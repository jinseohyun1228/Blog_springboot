package com.jsh.blog.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder  //빌더 페턴!
@Entity
public class Board {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false, length = 100)
  private String titlel;

  @Lob  //대용량 데이터
  private String content; //섬머 노트 라이브러리 <html>태그가 섞여서 디자인이 됨.

  @ColumnDefault("0")
  private int count;

  /*
   DB는 오브젝트를 저장할 수 없다. 따라서 FK를 사용한다.
   자바는 오브젝트를 저장할 수 있다.
   ....?

   어떻게 DB에 이를 올릴 것인가? DB에는 테이블의 기본키가 올라간다.
    */
  @ManyToOne  //여기가 다수고 저기가 하나  -> 한명의 유저는 여러개의 게시글을 쓸 수 있다.
  @JoinColumn(name = "userID")  //필드명
  private User user;


  //하나의 게시물은 여러개의 답변을 가질 수 있다.
  @OneToMany(mappedBy = "board" , fetch = FetchType.EAGER)
  //mappedBy -> 연관관계의 주인이 아니다. FK가 아니고, 이것을 DB의 속성으로 만들지마세요
  //fetch전략 - 매번들고오기
  private List<Reply> reply;


  @CreationTimestamp
  private Timestamp createDate;
}
