package com.example.homework2_todolist.dto;

import com.example.homework2_todolist.entity.CardEntity;

import java.time.LocalDateTime;

public record CardResponseDto(
//    ======================================================= 5 , 11 public class -> public record
     Long cardId,
     String title,
     String author,
     String content,
     LocalDateTime createdAt
) {
       public CardResponseDto(CardEntity savePost) {
        this(savePost.getCardId(), savePost.getTitle(), savePost.getAuthor(),
            savePost.getContents(),savePost.getCreatedAt());
    }
//    ======================================= 10, 11 this( ); 로 바꿈.
}
