package com.jsh.blog.handler;

import com.jsh.blog.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice //어디서든
@RestController
public class GlobalExceptionHandler {
  @ExceptionHandler(value = Exception.class)
  public ResponseDto<String> handleArgumentException(Exception e) {
    return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
  }

}