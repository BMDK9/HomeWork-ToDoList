package com.sparta.homework2_todolist.repository;

import com.sparta.homework2_todolist.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardJpaRepository extends JpaRepository<CardEntity, Long> {
    List<CardEntity> findAllByOrderByCreatedAtDesc();
}
//================================================================= 9