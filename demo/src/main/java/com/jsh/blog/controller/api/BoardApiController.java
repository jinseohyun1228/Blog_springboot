package com.jsh.blog.controller.api;

import com.jsh.blog.config.auth.PrincipalDetail;
import com.jsh.blog.dto.ReplySaveRequestDto;
import com.jsh.blog.dto.ResponseDto;
import com.jsh.blog.model.Board;
import com.jsh.blog.model.Reply;
import com.jsh.blog.model.User;
import com.jsh.blog.repository.BoardRepository;
import com.jsh.blog.service.BoardService;
import com.jsh.blog.service.ReplyService;
import com.jsh.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

  @Autowired
  private BoardService boardService;

  @Autowired
  private ReplyService replyService;

  @PostMapping("/api/board")
  public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
    boardService.saveBoard(board, principal.getUser());
    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
  }

  @DeleteMapping("/api/board/{id}")
  public ResponseDto<Integer> deleteById(@PathVariable int id) {
    boardService.deletePost(id);
    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
  }

  @PutMapping("/api/board/{id}")
  public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) { // dataType: "json"
    boardService.update(id,board);
    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
  }

  @PostMapping("/api/board/{boardId}/reply")
  public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto) {
    //데이터를 받을 때 컨트롤러에서 Dto를 만들어주는 것이 좋다
    replyService.writeReply(replySaveRequestDto);
    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

  }
  @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
  public ResponseDto<Integer> replyDelete(@PathVariable int replyId) {
    replyService.deleteReply(replyId);
    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
  }
}


