package com.jsh.blog.controller;

import com.jsh.blog.config.auth.PrincipalDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BoardController {


  @GetMapping({"","/"})
  public String index(){
    System.out.println("HomeScreen");
    return "index";
  }

  @GetMapping("/board/saveForm")
  public String saveForm() {
    return "board/saveForm";
  }
}
