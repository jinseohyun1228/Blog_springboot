package com.jsh.blog.controller;

import com.jsh.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

  @Autowired
  private BoardService boardService;

  @GetMapping({"","/"})
  public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
    model.addAttribute("boards",boardService.getPostList(pageable));
    return "index"; //viewResolver
  }

  @GetMapping("/board/saveForm")
  public String saveForm() {
    return "board/saveForm";
  }

  @GetMapping("/board/{id}/updateForm")
  public String updateForm(@PathVariable int id,Model model){
    model.addAttribute("board", boardService.viewPostDetails(id));
    return "board/updateForm";
  }

  @GetMapping("/board/{id}")
  public String viewPostDetails(@PathVariable int id,Model model){
    model.addAttribute("board", boardService.viewPostDetails(id));
    return "board/detail";
  }
}
