package com.sparta.homework2_todolist.domain.card.dto;

import com.sparta.homework2_todolist.domain.card.entity.Card;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardResponseDto {
//    ======================================================= 5 , 11 public class -> public record
    private String title;
    private String content;
    private Boolean isDone;
    private Boolean isHidden;
    private LocalDateTime createdAt;

    @Builder
    private CardResponseDto(String title, String content, Boolean isDone, Boolean isHidden, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.isDone = isDone;
        this.isHidden = isHidden;
        this.createdAt = createdAt;

    }
//    ======================================= 10, 11 this( ); 로 바꿈.

    public static CardResponseDto of (Card card) {

        return CardResponseDto.builder()
            .title(card.getTitle())
            .content(card.getContents())
            .isDone(card.isDone())
            .isHidden(card.isHidden())
            .createdAt(card.getCreatedAt())
            .build();
    }
}
