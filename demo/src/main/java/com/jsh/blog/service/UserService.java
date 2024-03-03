package com.jsh.blog.service;

import com.jsh.blog.config.auth.PrincipalDetail;
import com.jsh.blog.model.Board;
import com.jsh.blog.model.RoleType;
import com.jsh.blog.model.User;
import com.jsh.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
  public User findUser(String username) {
    User user = userRepository.findByUsername(username).orElseGet(()->{
      return new User();
    });
    return user;
  }

  @Transactional
  public void joinUser(User user) {
    String rowPassword = user.getPassword();
    String encPassword = encoder.encode(rowPassword);  //해쉬
    user.setPassword(encPassword);
    user.setRole(RoleType.USER);
    userRepository.save(user);
  }

  @Transactional
  public void update(User requestUser, PrincipalDetail principalDetail) {
    // 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화 시키고(= select),
    // 영속화된 User 오브젝트를 수정, 영속화된 오브젝트를 변경하면 자동으로 DB에 update

    User principal = userRepository.findById(requestUser.getId())
            .orElseThrow(() -> {
              return new IllegalArgumentException("해당 회원이 없습니다.");
            });
    System.out.println("requestUser = getPassword " + requestUser.getPassword());
    if ((principal.getOauth() == null && principal.getOauth().equals("")) && (requestUser.getPassword() != null && !requestUser.getPassword().isEmpty()) ) {
      String rowPassword = requestUser.getPassword();
      String encPassword = encoder.encode(rowPassword);
      principal.setPassword(encPassword);
    }

    principal.setEmail(requestUser.getEmail());

    principalDetail.setUser(principal);

    // 회원 함수 종료 = 서비스 종료 = 트랜잭션 종료 = commit

  }
//  @Transactional(readOnly = true) //select할때 트랜잭션 시작,
//  // 서비스 종료할 때 트랜잭션을 종료하고 같은 트랜잭션내에 정합성 유지 보장!
//  public User loginUser(User user){
//    System.out.println("1번");
//    return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
//  }
}

