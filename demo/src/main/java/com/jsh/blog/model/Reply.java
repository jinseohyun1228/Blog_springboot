package com.jsh.blog.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false, length = 200)
  private String content;

  //?누가? 어느테이블에?
  /*연관관계
    @OneToMany    //하나의 댓글은 여러개의 테이블로 갈 수 있다.--> X
    @OneToOne     //게시글 하나는 하나의 답변을 가질 수 있다. ---> x
   */
  //하나의 게시글에는 여러개의 답변이 올 수 있다.
  @ManyToOne    //여러개의 댓글은 하나의 테이블에 갈 수 있다.
  @JoinColumn(name = "boardId")
  private Board board;

  //댓글은 하나의 작성자를 가지고, 작성자는 여러개의 답변을 가질 수 있다.
  // -> 여러개의 댓글은 하나의 작성자를 가진다.
  @ManyToOne
  @JoinColumn(name = "userID")
  private User user;

  @CreationTimestamp
  private Timestamp createDate;

}
