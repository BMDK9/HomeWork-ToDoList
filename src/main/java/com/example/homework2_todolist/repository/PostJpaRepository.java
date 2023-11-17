package com.example.homework2_todolist.repository;

import com.example.homework2_todolist.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {
}
//================================================================= 9