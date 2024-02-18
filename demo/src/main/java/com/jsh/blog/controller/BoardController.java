package com.jsh.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BoardController {

  @GetMapping({"","/"})
  public String index(){
    System.out.println("홈 화면 출력");
    return "index";
  }
}
