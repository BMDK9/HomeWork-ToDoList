package com.sparta.homework2_todolist.domain.card.service;

import com.sparta.homework2_todolist.domain.card.dto.CardRequestDto;
import com.sparta.homework2_todolist.domain.card.dto.CardResponseDto;
import com.sparta.homework2_todolist.domain.card.entity.Card;
import com.sparta.homework2_todolist.domain.card.repository.CardRepository;
import com.sparta.homework2_todolist.domain.users.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {
    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;

    @DisplayName("할일 카드 추가 테스트")
    @Test
    void addToDo() {
        User user1 = User.builder().userId(1L).username("보라돌이")
            .build();

        Card card1 = Card.builder().title("할일 카드").contents("추가 테스트").user(user1)
            .isDone(false).isHidden(false)
            .build();

        CardRequestDto cardRequestDto = CardRequestDto.builder()
            .title("할일 카드").contents("추가 테스트")
            .build();
        // given
        given(cardRepository.save(any(Card.class))).willReturn(card1);

        // when
        CardResponseDto result = cardService.addToDo(cardRequestDto, user1);

        // then
        assertThat(result).extracting("title", "contents")
            .contains("할일 카드", "추가 테스트");

        assertEquals(cardRequestDto.getTitle(), result.getTitle());
        assertEquals(cardRequestDto.getContents(), result.getContents());
    }

    @DisplayName("할일 카드 가져오기 테스트")
    @Test
    void getCard() {
        // given
        User user1 = User.builder().userId(1L).username("보라돌이")
            .build();

        User user2 = User.builder().userId(2L).username("뚜비")
            .build();

        Card card2 = Card.builder().title("제목 제목").contents("내용 내용").user(user2)
            .isDone(false).isHidden(false)
            .build();

        given(cardRepository.findById(anyLong())).willReturn(Optional.of(card2));

        // when
        CardResponseDto card = cardService.getCard(2L, user1);

        // then
        assertThat(card).extracting("title", "contents", "isDone", "isHidden")
            .contains("제목 제목", "내용 내용", false, false);
    }

    @DisplayName("할일 카드 목록 조회")
    @Test
    void getCards() {
        // given
        User user1 = User.builder().userId(1L).username("보라돌이")
            .build();

        User user2 = User.builder().userId(2L).username("뚜비")
            .build();

        Card card1 = Card.builder().title("할일 카드").contents("추가 테스트").user(user1)
            .isDone(false).isHidden(false)
            .build();

        Card card2 = Card.builder().title("제목 제목").contents("내용 내용").user(user2)
            .isDone(false).isHidden(false)
            .build();

        given(cardRepository.findAllByOrderByCreatedAtDesc()).willReturn(List.of(card1, card2));

        // when
        List<CardResponseDto> cards = cardService.getCards(user1);

        // then
        assertThat(cards).hasSize(2);
        System.out.println(cards.get(0).getTitle());
        assertThat(cards).extracting("title", "contents", "isDone", "isHidden")
            .contains(
                tuple("할일 카드", "추가 테스트", false, false),
                tuple("제목 제목", "내용 내용", false, false));
    }

    @DisplayName("할일 카드 수정 테스트")
    @Test
    void updateCard() {
        // given
        User user2 = User.builder().userId(2L).username("뚜비")
            .build();

        Card card2 = Card.builder().title("제목 제목").contents("내용 내용").user(user2)
            .isDone(false).isHidden(false)
            .build();

        CardRequestDto cardRequestDto2 = CardRequestDto.builder()
            .title("할일 제목 수정").contents("할일 내용 수정")
            .build();

        given(cardRepository.findById(anyLong())).willReturn(Optional.of(card2));

        // when
        CardResponseDto result = cardService.updateCard(2L, cardRequestDto2, user2);

        // then
        assertThat(result).extracting("title", "contents")
            .contains("할일 제목 수정", "할일 내용 수정");
    }

    @DisplayName("할일 카드 삭제 테스트")
    @Test
    void deleteCard() {
        // given
        User user2 = User.builder().userId(2L).username("뚜비")
            .build();

        Card card2 = Card.builder().title("제목 제목").contents("내용 내용").user(user2)
            .isDone(false).isHidden(false)
            .build();

        given(cardRepository.findById(anyLong())).willReturn(Optional.of(card2));

        // when
        cardService.deleteCard(1L, user2);

        // then
        Mockito.verify(cardRepository, Mockito.times(1)).delete(any(Card.class));
    }

    @DisplayName("카드 완료 테스트")
    @Test
    void changeCardStatus() {
        // given
        User user1 = User.builder().userId(1L).username("보라돌이")
            .build();

        Card card1 = Card.builder().title("할일 카드").contents("추가 테스트").user(user1)
            .isDone(false).isHidden(false)
            .build();

        given(cardRepository.findById(anyLong())).willReturn(Optional.ofNullable(card1));

        // when
        CardResponseDto result = cardService.changeCardStatus(1L, user1);

        // then
        assertThat(result.getIsDone()).isEqualTo(true);
    }

    @DisplayName("카드 숨김 테스트")
    @Test
    void concealCard() {
        // given
        User user2 = User.builder().userId(2L).username("뚜비")
            .build();

        Card card2 = Card.builder().title("제목 제목").contents("내용 내용").user(user2)
            .isDone(false).isHidden(false)
            .build();

        given(cardRepository.findById(anyLong())).willReturn(Optional.ofNullable(card2));

        // when
        CardResponseDto result = cardService.concealCard(2L, user2);

        // then
        assertThat(result.getIsHidden()).isEqualTo(true);
    }

}