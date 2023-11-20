package com.sparta.homework2_todolist.repository;

import com.sparta.homework2_todolist.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByOrderByCreatedAtDesc();
}
//================================================================= 9