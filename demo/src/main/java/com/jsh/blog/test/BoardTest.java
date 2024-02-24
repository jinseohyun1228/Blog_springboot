package com.jsh.blog.test;

import com.jsh.blog.model.Board;
import com.jsh.blog.model.RoleType;
import com.jsh.blog.model.User;
import com.jsh.blog.repository.BoardRepository;
import com.jsh.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardTest {

  @Autowired
  UserRepository userRepository;

  @Autowired
  BoardRepository boardRepository;

  @PostMapping("/test/board")
  public String boradTest(Board board) {
    board.setCount(0);
    board.setUser(userRepository.findById(1).get());
    boardRepository.save(board);
    return "게시판 저장이 완료되었습니다.";
  }
  @PostMapping("/dummy/join")
  public String join(User user) { // key=value (약속된 규칙)
    System.out.println("id : "+user.getId());
    System.out.println("username : "+user.getUsername());
    System.out.println("password : "+user.getPassword());
    System.out.println("email : "+user.getEmail());
    System.out.println("role : "+user.getRole());
    System.out.println("createDate : "+user.getCreateDate());

    user.setRole(RoleType.USER);
    userRepository.save(user);
    return "회원가입이 완료되었습니다.";
  }

}
