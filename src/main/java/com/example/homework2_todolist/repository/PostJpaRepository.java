package com.example.homework2_todolist.repository;

import com.example.homework2_todolist.entity.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostJpaRepository extends JpaRepository<ToDoEntity, Long> {
    List<ToDoEntity> findAllByOrderByCreatedAtDesc();
}
//================================================================= 9