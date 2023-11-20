package com.sparta.homework2_todolist.repository;

import com.sparta.homework2_todolist.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
