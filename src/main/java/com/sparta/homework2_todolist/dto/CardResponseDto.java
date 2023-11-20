package com.sparta.homework2_todolist.dto;

import com.sparta.homework2_todolist.entity.Card;

import java.time.LocalDateTime;

public record CardResponseDto(
//    ======================================================= 5 , 11 public class -> public record
    String title,
    String content,
    boolean done,
    boolean hidden,
    LocalDateTime createdAt
) {
    public CardResponseDto(Card saveCard) {
        this(saveCard.getTitle(),
            saveCard.getContents(),
            saveCard.isDone(),
            saveCard.isHidden(),
            saveCard.getCreatedAt());
    }
//    ======================================= 10, 11 this( ); 로 바꿈.
}
