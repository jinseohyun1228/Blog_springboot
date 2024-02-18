package com.jsh.blog.service;

import com.jsh.blog.model.RoleType;
import com.jsh.blog.model.User;
import com.jsh.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service  //스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해준다. Ioc
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder encoder;

  @Transactional
  public void joinUser(User user) {
    String rowPassword = user.getPassword();
    String encPassword = encoder.encode(rowPassword);  //해쉬
    user.setPassword(encPassword);
    user.setRole(RoleType.USER);
    userRepository.save(user);
  }

//  @Transactional(readOnly = true) //select할때 트랜잭션 시작,
//  // 서비스 종료할 때 트랜잭션을 종료하고 같은 트랜잭션내에 정합성 유지 보장!
//  public User loginUser(User user){
//    System.out.println("1번");
//    return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
//  }
}

