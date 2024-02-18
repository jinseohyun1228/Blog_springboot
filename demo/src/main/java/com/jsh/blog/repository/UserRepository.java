package com.jsh.blog.repository;

import com.jsh.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//DAO 의 기능! & 자동으로 빈 등록이 된다.
//@Repository -> 생략이 가능하다.
public interface UserRepository extends JpaRepository<User, Integer> {//기본적인 CRUD 가능

}


  /*
  //방법1. JPA 네이밍 전략을 따르는 함수명을 만들면된다.
  //아래 함수명은 다음과 같은 기능 SELECT * FROM user WHERE username = ?1 AND = password = ?2
  User findByUsernameAndPassword(String username, String password);

  //방법1. @Query 이용 + nativeQuery = true
  @Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2",nativeQuery = true)
  User login(String username, String password);
  */