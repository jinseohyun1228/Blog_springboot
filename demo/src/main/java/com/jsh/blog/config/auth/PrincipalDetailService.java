package com.jsh.blog.config.auth;

import com.jsh.blog.model.User;
import com.jsh.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  //스프링이 로그인 요청을 가로챌 때 username,password 변수 2개를 가로 챈다.
  // password 처리는 알아서
  // username 은 DB에 있는지 리턴
  @Override // 우리의 user 타입으로 담아주는 역할을 해준다.!! override 필수
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User principal = userRepository.findByUsername(username)
            .orElseThrow(()->{
              return new UsernameNotFoundException("해당사용자를 찾을 수 없습니다. : "+ username);
            });
    return new PrincipalDetail(principal); //시큐리티 세션에 우리가 만든 유저 정보가 저장된다.

  }
}
