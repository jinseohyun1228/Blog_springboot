package com.jsh.blog.service;

import com.jsh.blog.model.Board;
import com.jsh.blog.model.Reply;
import com.jsh.blog.model.User;
import com.jsh.blog.repository.BoardRepository;
import com.jsh.blog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReplyService {

  @Autowired
  private ReplyRepository replyRepository;

  @Autowired
  private BoardRepository boardRepository;

  @Transactional
  public void writeReply(User user, int boardId, Reply requsetReply) {
    Board board = boardRepository.findById(boardId).orElseThrow(()->{
      return new IllegalArgumentException("댓글 쓰기 실패 : 게시글 아이디를 찾을 수 없습나다.");
    });

    requsetReply.setBoard(board);
    requsetReply.setUser(user);
    replyRepository.save(requsetReply);
  }
}
