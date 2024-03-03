package com.jsh.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder  //빌더 페턴!
@Entity //스프링 부트가 시작될 떄 User 클래스가 MySQL에 테이블이 생성된다.
//@DynamicInsert -> insert 시에 null 인 필드 제외
public class User{

  @Id // 해당 필드?데이터?변수?를 기본키로 설정하는 어노테이션
  @GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
  private int id;  //autu_increment

  @Column(nullable = false,length = 30,unique = true) //널이 될 수 없다. 30자를 넘을 수 없다.
  private String username;

  @Column(nullable = false,length = 100)
  private String password;

  @Column(length = 50)
  private String email;

  //  @ColumnDefault("'user'")
  @Enumerated(EnumType.STRING)
  private RoleType role;


  private String oauth;

  @CreationTimestamp //시간이 자동으로 입력이 된다.
  private Timestamp createDate;

}