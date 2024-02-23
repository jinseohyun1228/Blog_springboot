package com.jsh.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//인증이 안된 사용자들이 출입할 수 있는 경로 -> */auth/*
//그냥 주소가 / -> index/js도 허용
//static 이하에 있는 /js/** , /css/**, /image/**는 허용

@Controller
public class UserController {

  @GetMapping("/auth/joinForm")
  public String joinForm() {
    System.out.println("goToJoiinScreen");
    return "user/joinForm";
  }

  @GetMapping("/auth/loginForm")
  public String loginForm() {
    System.out.println("goToLoginScreen");
    return "user/loginForm";
  }
}
