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
import org.springframework.web.bind.annotation.RequestBody;

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

  @Transactional(readOnly = true)
  public Page<Board> getPostList(Pageable pageable) {
    return boardRepository.findAll(pageable);
  }

  @Transactional(readOnly = true)
  public Board viewPostDetails(int id) {
    return boardRepository.findById(id)
            .orElseThrow(()->{
              return new IllegalArgumentException("글 상세보기 실패: 아이디어를 찾을 수 없습니다.");
            });
  }

  @Transactional
  public void deletePost(int id) {
    boardRepository.deleteById(id);
  }

  @Transactional
  public void update(int id, @RequestBody Board requsetBoard) {  //수정을 위해서는 영속화
    Board board = boardRepository.findById(id)
            .orElseThrow(()->{
              return new IllegalArgumentException("글 상세보기 실패: 아이디어를 찾을 수 없습니다.");
            });
    board.setTitle(requsetBoard.getTitle());
    board.setContent(requsetBoard.getContent());

    //해당 함수 종료시(=service가 종료될때) -> 트랜잭션이 종료되고, 더티 체킹으로 인해 자동업데이트 db flush
  }
}

