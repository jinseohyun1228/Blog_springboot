package com.jsh.blog.controller;

import com.jsh.blog.model.KakaoProfile;
import com.jsh.blog.model.OAuthToken;
import com.jsh.blog.model.User;
import com.jsh.blog.service.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KakaoController {
  @Autowired
  KakaoService kakaoService;

  @GetMapping("/auth/kakao/callback")
  public String kakaCallback(@RequestParam String code){
    OAuthToken oauthToken = kakaoService.토큰받기(code);

    KakaoProfile kakaoProfile = kakaoService.사용자조회하기(oauthToken);

    User user = kakaoService.사용자등록하기(kakaoProfile);
    return "index";
  }

}
