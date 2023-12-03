package com.sparta.homework2_todolist.domain.card.entity;

import com.sparta.homework2_todolist.domain.card.dto.CardRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class CardTest {

    @Test
    @DisplayName("카드 수정 정상 작동 테스트")
    void test1() {
        //given
        Card card = new Card();
        CardRequestDto cardRequestDto = CardRequestDto.builder()
            .title("Dto에 빌드 만들어도")
            .content("괜찮은거 맞나?")
            .build();

        //when
        card.update(cardRequestDto.getTitle(), cardRequestDto.getContent());

        //then
        assertNotNull(card.getTitle());
        assertNotNull(card.getContents());
        assertEquals("Dto에 빌드 만들어도", card.getTitle());
        assertEquals("괜찮은거 맞나?", card.getContents());
        org.assertj.core.api.Assertions.assertThat(card.getContents()).isEqualTo("괜찮은거 맞나?");
    }

    @Test
    @DisplayName("카드 완료 성공 테스트")
    void test2() {
        // given
        Card card = Card.builder()
            .isDone(false)
            .build();

        //when
        card.changeStatus();

        //then
        assertNotNull(card.getIsDone());
        assertEquals(true, card.getIsDone());
    }

    @Test
    @DisplayName("카드 숨김 성공 테스트")
    void test3() {
        // given
        Card card = Card.builder()
            .isHidden(false)
            .build();

        //when
        card.hideCard();

        //then
        assertNotNull(card.getIsHidden());
        assertEquals(true, card.getIsHidden());
    }
}