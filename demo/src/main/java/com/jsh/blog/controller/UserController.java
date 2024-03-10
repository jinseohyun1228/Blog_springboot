package com.jsh.blog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsh.blog.model.KakaoProfile;
import com.jsh.blog.model.OAuthToken;
import com.jsh.blog.model.RoleType;
import com.jsh.blog.model.User;
import com.jsh.blog.service.UserService;
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
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

//인증이 안된 사용자들이 출입할 수 있는 경로 -> */auth/*
//그냥 주소가 / -> index/js도 허용
//static 이하에 있는 /js/** , /css/**, /image/**는 허용

@Controller
public class UserController {



  @GetMapping("/auth/joinForm")
  public String joinForm() {
//    System.out.println("goToJoiinScreen");
    return "user/joinForm";
  }

  @GetMapping("/auth/loginForm")
  public String loginForm() {
//    System.out.println("goToLoginScreen");
    return "user/loginForm";
  }

  @GetMapping("/user/updateForm")
  public String updateForm() {
//    System.out.println("/user/updateForm");
    return "user/updateForm";
  }
}
