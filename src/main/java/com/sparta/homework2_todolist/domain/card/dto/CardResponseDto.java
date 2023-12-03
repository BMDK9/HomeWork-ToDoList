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
    private String contents;
    private Boolean isDone;
    private Boolean isHidden;
    private LocalDateTime createdAt;

    @Builder
    private CardResponseDto(String title, String contents, Boolean isDone, Boolean isHidden, LocalDateTime createdAt) {
        this.title = title;
        this.contents = contents;
        this.isDone = isDone;
        this.isHidden = isHidden;
        this.createdAt = createdAt;

    }
//    ======================================= 10, 11 this( ); 로 바꿈.

    public static CardResponseDto of (Card card) {

        return CardResponseDto.builder()
            .title(card.getTitle())
            .contents(card.getContents())
            .isDone(card.getIsDone())
            .isHidden(card.getIsHidden())
            .createdAt(card.getCreatedAt())
            .build();
    }
}
