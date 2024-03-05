package com.jsh.blog.repository;

import com.jsh.blog.dto.ReplySaveRequestDto;
import com.jsh.blog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

  @Modifying
  @Query(value = "INSERT INTO reply(content, userId, boardId, createDate) VALUES(?1,?2,?3,now())" , nativeQuery = true)
  int nativeSave(String content, int userId, int boardId); //업데이트 된 행의 개수를 리턴해준다.
}
