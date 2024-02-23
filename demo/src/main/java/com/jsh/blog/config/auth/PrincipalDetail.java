package com.jsh.blog.config.auth;

import com.jsh.blog.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
//스프링 시큐리티의 고유한 세션 저장소에 저장을 해준다.
public class PrincipalDetail implements UserDetails {
  private User user; //컴포지션  우리의 유저 엔티티를 이렇게 가지고 있네..? 신기하다... 그러면 앞으로는 이 PrincipalDetail를 이용해서 엔티티에 접근하는건가? 정말 신기하다묭~

  public PrincipalDetail(User user) {
    this.user = user;
  }
  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  //계정이 살아있는지 리턴 : ture -> 만료 X / false -> 만료됨
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  //계정이 잠기지 않았는지 리턴 : true -> 잠기지 않음
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  //비밀번호가 만료되지 않았는지 리턴 : ture -> 만료 X
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  //계정이 활성화되어있는지 리턴 : true -> 만료 X
  @Override
  public boolean isEnabled() {
    return true;
  }

  //계정의 권한을 리턴...!!
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() { //타입이 특이하군용..?!
    Collection<GrantedAuthority> collection = new ArrayList<>();
    /*collection.add(new GrantedAuthority() {
      @Override
      public String getAuthority() {
        return "ROLE_"+user.getRole();    //규칙이라고 함...니다...
      }
    });
     */
    collection.add(()->{return "ROLE_"+user.getRole();});
    return collection;
  }
}
