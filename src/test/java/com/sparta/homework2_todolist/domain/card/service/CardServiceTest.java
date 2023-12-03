package com.sparta.homework2_todolist.domain.card.service;

import com.sparta.homework2_todolist.domain.card.dto.CardRequestDto;
import com.sparta.homework2_todolist.domain.card.dto.CardResponseDto;
import com.sparta.homework2_todolist.domain.card.entity.Card;
import com.sparta.homework2_todolist.domain.card.repository.CardRepository;
import com.sparta.homework2_todolist.domain.users.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {

    private User user1, user2;

    private Card card1, card2;

    private CardRequestDto cardRequestDto1, cardRequestDto2;

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;

    @BeforeEach
    void setUp() {
        user1 = User.builder().userId(1L).username("성훈")
            .build();

        user2 = User.builder().userId(2L).username("김성훈")
            .build();

        card1 = Card.builder().title("할일 카드").contents("추가 테스트").user(user1)
            .isDone(false).isHidden(false)
            .build();

        card2 = Card.builder().title("제목 제목").contents("내용 내용").user(user2)
            .isDone(false).isHidden(false)
            .build();

        cardRequestDto1 = CardRequestDto.builder()
            .title("할일 카드").content("추가 테스트")
            .build();

        cardRequestDto2 = CardRequestDto.builder()
            .title("할일 제목 수정").content("할일 내용 수정")
            .build();
    }

    @DisplayName("할일 카드 추가 테스트")
    @Test
    void addToDo() {
        // given
        BDDMockito.given(cardRepository.save(any(Card.class))).willReturn(card1);

        // when
        CardResponseDto result = cardService.addToDo(cardRequestDto1, user1);

        // then
        assertThat(result).extracting("title", "content")
            .contains("할일 카드", "추가 테스트");

//        assertEquals(cardRequestDto.getTitle(), result.getTitle());
//        assertEquals(cardRequestDto.getContent(), result.getContent());
    }

    @DisplayName("할일 카드 가져오기 테스트")
    @Test
    void getCard() {
        // given
        BDDMockito.given(cardRepository.findById(anyLong())).willReturn(Optional.of(card2));

        // when
        CardResponseDto card = cardService.getCard(2L, user1);

        // then
        assertThat(card).extracting("title", "content", "isDone", "isHidden")
            .contains("제목 제목", "내용 내용", false, false);
    }

    @DisplayName("할일 카드 수정 테스트")
    @Test
    void updateCard() {
        // given
        BDDMockito.given(cardRepository.findById(anyLong())).willReturn(Optional.of(card2));

        // when
        CardResponseDto result = cardService.updateCard(2L, cardRequestDto2, user2);

        // then
        assertThat(result).extracting("title", "content")
            .contains("할일 제목 수정", "할일 내용 수정");
    }

    @DisplayName("할일 카드 삭제 테스트")
    @Test
    void deleteCard() {
        // given
        BDDMockito.given(cardRepository.findById(anyLong())).willReturn(Optional.of(card2));

        // when
        cardService.deleteCard(1L, user2);

        // then
        Mockito.verify(cardRepository, Mockito.times(1)).delete(any(Card.class));
    }
}