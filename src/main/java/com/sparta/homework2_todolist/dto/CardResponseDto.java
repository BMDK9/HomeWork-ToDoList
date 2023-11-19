package com.sparta.homework2_todolist.dto;

import com.sparta.homework2_todolist.entity.CardEntity;

import java.time.LocalDateTime;

public record CardResponseDto(
//    ======================================================= 5 , 11 public class -> public record
     String title,
     String content,
     boolean done,
     LocalDateTime createdAt
) {
       public CardResponseDto(CardEntity saveCard) {
        this(saveCard.getTitle(),
            saveCard.getContents(),
            saveCard.isDone(),
            saveCard.getCreatedAt());
    }
//    ======================================= 10, 11 this( ); 로 바꿈.
}
