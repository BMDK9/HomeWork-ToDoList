package com.sparta.homework2_todolist.domain.card.entity;

import com.sparta.homework2_todolist.domain.card.dto.CardRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class CardTest {
    Card card;

    @Test
    @DisplayName("카드 업데이트 테스트")
    void test1 () {
        //given
        Card card = new Card();

        CardRequestDto cardRequestDto = new CardRequestDto();
        cardRequestDto.setTitle("세터 쓰기 싫은데에");
        cardRequestDto.setContent("안쓰는 방법 알려줄 사람?");

        //when
        card.update(cardRequestDto);

        //then
        assertNotNull(card.getTitle());
        assertNotNull(card.getContents());
        assertEquals("세터 쓰기 싫은데에", card.getTitle());
        assertEquals("안쓰는 방법 알려줄 사람?", card.getContents());
    }

    @Test
    @DisplayName("카드 완료 처리 테스트")
    void test2 () {
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
}