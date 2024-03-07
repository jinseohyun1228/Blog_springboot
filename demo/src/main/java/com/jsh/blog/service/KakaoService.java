package com.jsh.blog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsh.blog.model.KakaoProfile;
import com.jsh.blog.model.OAuthToken;
import com.jsh.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoService {

  @Value("${jsh.key}")
  private String jshKey;

  @Autowired
  UserService userService;

  @Autowired
  private AuthenticationManager authenticationManager;

  public OAuthToken 토큰받기(String code) {
//    POST 방식으로 key = value 데이터를 요청 (Retrofit2, OkHttp)
    RestTemplate restTemplate = new RestTemplate();

    //헤더 객체 생성
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

    //바디 객체 생성
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "authorization_code");
    params.add("client_id", "f1bbd2060c770d4aeeb7b81068bebbd6");
    params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
    params.add("code", code);

    //바디데이터와 헤더를 가지고 있는 엔티티
    HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
            new HttpEntity<>(params, httpHeaders);

    ResponseEntity<String> responseEntity = restTemplate.exchange(
            "https://kauth.kakao.com/oauth/token",
            HttpMethod.POST,
            kakaoTokenRequest,
            String.class
    );

    ObjectMapper objectMapper = new ObjectMapper();

    OAuthToken oauthToken = null;
    try {
      oauthToken = objectMapper.readValue(responseEntity.getBody(), OAuthToken.class);
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return oauthToken;
  }

  public KakaoProfile 사용자조회하기(OAuthToken oAuthToken) {
    //POST 방식으로 key = value 데이터를 요청 (Retrofit2, OkHttp)
    RestTemplate restTemplate2 = new RestTemplate();

    //헤더 객체 생성
    HttpHeaders httpHeaders2 = new HttpHeaders();
    httpHeaders2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
    httpHeaders2.add("Authorization", "Bearer " + oAuthToken.getAccess_token());
    ;

    //바디데이터와 헤더를 가지고 있는 엔티티
    HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
            new HttpEntity<>(httpHeaders2);

    ResponseEntity<String> kakaoProfileResponse = restTemplate2.exchange(
            "https://kapi.kakao.com/v2/user/me",
            HttpMethod.POST,
            kakaoProfileRequest,
            String.class
    );



    ObjectMapper objectMapper2 = new ObjectMapper();

    KakaoProfile kakaoProfile = null;
    try {
      kakaoProfile = objectMapper2.readValue(kakaoProfileResponse.getBody(), KakaoProfile.class);
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    return kakaoProfile;
  }

  public User 사용자등록하기(KakaoProfile kakaoProfile) {
    //
    User kakaoUser = User.builder()
            .username(kakaoProfile.getProperties().getNickname()+"_"+kakaoProfile.getId())
            .password(String.valueOf(jshKey))
            .email("kakao@email")
            .oauth("kakao")
            .build();


    User originUser = userService.findUser(kakaoUser.getUsername());

    if (originUser.getUsername() == null) {
      userService.joinUser(kakaoUser);
    }

    //로그인 처리
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), jshKey));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    return kakaoUser;
  }
}
