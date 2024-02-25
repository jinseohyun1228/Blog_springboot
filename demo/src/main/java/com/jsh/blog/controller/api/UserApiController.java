package com.jsh.blog.controller.api;

import com.jsh.blog.config.SecurityConfig;
import com.jsh.blog.config.auth.PrincipalDetail;
import com.jsh.blog.dto.ResponseDto;
import com.jsh.blog.model.Board;
import com.jsh.blog.model.RoleType;
import com.jsh.blog.model.User;
import com.jsh.blog.service.BoardService;
import com.jsh.blog.service.UserService;
//import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

  @Autowired
  private UserService userService;

  @Autowired
  private AuthenticationManager authenticationManager;

//  @Autowired //스프링이 빈으로 들고있기 때문에 의존성 주입으로도 받을 수 있다.
//  private HttpSession session;

  @PostMapping("/auth/joinProc")
  public ResponseDto<Integer> save(@RequestBody User user) {
//    System.out.println("UserApiController: save 호출");
    userService.joinUser(user);
    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
  }

  @PutMapping("/api/user")
  public ResponseDto<Integer> update(@RequestBody User user,@AuthenticationPrincipal PrincipalDetail principal) {
    userService.update(user,principal);

//    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
//    SecurityContextHolder.getContext().setAuthentication(authentication);

    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
  }
}
  /*@PostMapping("/api/user/login")
  public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
    System.out.println("UserApiController: login 호출");
    User principal = userService.loginUser(user); //접근 주체
    System.out.println(principal);
    if (principal != null) {
      session.setAttribute("principal",principal); //세션만들기... 헤더에서
      System.out.println("세션만들기 성공");
      return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }else {
      return new ResponseDto<Integer>(HttpStatus.EXPECTATION_FAILED.value(), -1);
    }
   */

