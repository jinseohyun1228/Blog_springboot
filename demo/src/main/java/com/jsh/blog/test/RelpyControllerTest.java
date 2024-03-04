package com.jsh.blog.test;

import com.jsh.blog.model.Board;
import com.jsh.blog.model.Reply;
import com.jsh.blog.repository.BoardRepository;
import com.jsh.blog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelpyControllerTest {

  @Autowired
  private BoardRepository boardRepository;

  @Autowired
  private ReplyRepository repository;

  @GetMapping("/test/board/{id}")
  public Board getBoard(@PathVariable int id) {
    return boardRepository.findById(id).get();  //jackson 라이브러리 (모델이 들고 있는 getter을 호출 한 결과를 json으로 리턴)
    //Board에 있는 getReply -> getBoard를 다시 리턴 ... -> 무한참조 발생
  }

  @GetMapping("test/reply/{id}")
  public Reply getReply(@PathVariable int id) {
    return repository.findById(id).get();
  }

}
