package com.jsh.blog.controller.api;

import com.jsh.blog.config.auth.PrincipalDetail;
import com.jsh.blog.dto.ResponseDto;
import com.jsh.blog.model.Board;
import com.jsh.blog.model.User;
import com.jsh.blog.service.BoardService;
import com.jsh.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardApiController {

  @Autowired
  private BoardService boardService;

  @PostMapping("/api/board")
  public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
    boardService.saveBoard(board,principal.getUser());
    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
  }

}


