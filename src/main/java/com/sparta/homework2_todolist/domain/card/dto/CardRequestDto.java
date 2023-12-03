package com.sparta.homework2_todolist.domain.card.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CardRequestDto {
    private String title;
    private String contents;

    @Builder
    private CardRequestDto (String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
// ============================================= 3