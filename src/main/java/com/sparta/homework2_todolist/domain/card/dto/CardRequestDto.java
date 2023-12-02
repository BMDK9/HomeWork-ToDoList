package com.sparta.homework2_todolist.domain.card.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CardRequestDto {
    private String title;
    private String content;

    @Builder
    private CardRequestDto (String title, String content) {
        this.title = title;
        this.content = content;
    }
}
// ============================================= 3