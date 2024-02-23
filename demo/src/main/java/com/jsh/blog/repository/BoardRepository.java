package com.jsh.blog.repository;

import com.jsh.blog.model.Board;
import com.jsh.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board, Integer> {//기본적인 CRUD 가능

}


