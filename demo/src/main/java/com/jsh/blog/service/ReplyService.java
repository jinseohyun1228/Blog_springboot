package com.jsh.blog.service;

import com.jsh.blog.dto.ReplySaveRequestDto;
import com.jsh.blog.model.Board;
import com.jsh.blog.model.Reply;
import com.jsh.blog.model.User;
import com.jsh.blog.repository.BoardRepository;
import com.jsh.blog.repository.ReplyRepository;
import com.jsh.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReplyService {

  @Autowired
  private ReplyRepository replyRepository;

  @Autowired
  private BoardRepository boardRepository;

  @Autowired
  private UserRepository userRepository;

  @Transactional
  public void writeReply(ReplySaveRequestDto replySaveRequestDto) {
    replyRepository.nativeSave(replySaveRequestDto.getContent(), replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId());
//    Board board = boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(()->{
//      return new IllegalArgumentException("댓글 쓰기 실패 : 게시글 아이디를 찾을 수 없습나다.");
//    });
//
//    User user = userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(()->{
//      return new IllegalArgumentException("댓글 쓰기 실패 : 댓글 작성자 아이디를 찾을 수 없습나다.");
//    });
//
//    Reply reply = Reply.builder()
//            .user(user)
//            .board(board)
//            .content(replySaveRequestDto.getContent())
//            .build();
//
//    replyRepository.save(reply);
  }

  @Transactional
  public void deleteReply(int replyId) {
    replyRepository.deleteById(replyId);
  }
}
