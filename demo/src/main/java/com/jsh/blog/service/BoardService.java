package com.jsh.blog.service;

import com.jsh.blog.model.Board;
import com.jsh.blog.model.RoleType;
import com.jsh.blog.model.User;
import com.jsh.blog.repository.BoardRepository;
import com.jsh.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service  //스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해준다. Ioc
public class BoardService {

  @Autowired
  private BoardRepository boardRepository;

  @Autowired
  UserRepository userRepository;

  @Transactional
  public void saveBoard(Board board,User user) {
    board.setCount(0);
    board.setUser(user);
    System.out.println("ItWorksUpToThisPoint");
    boardRepository.save(board);
  }

  public Page<Board> getPostList(Pageable pageable) {
    return boardRepository.findAll(pageable);
  }
}

