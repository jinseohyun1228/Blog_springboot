package com.jsh.blog.test;

import com.jsh.blog.model.User;
import com.jsh.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserTest {

  @Autowired
  private UserRepository userRepository;

  @PostMapping("/test/findUser")
  public User findUser(@RequestBody User user) {
    User user1 = userRepository.findById(user.getId())
            .orElseThrow(()->{
              return new IllegalArgumentException("해당 회원이 없습니다.");
            });
    return user1;
  }

}
