package com.sparta.homework2_todolist.domain.card.service;

import com.sparta.homework2_todolist.domain.card.dto.CardRequestDto;
import com.sparta.homework2_todolist.domain.card.dto.CardResponseDto;
import com.sparta.homework2_todolist.domain.card.entity.Card;
import com.sparta.homework2_todolist.domain.card.exception.CardErrorCode;
import com.sparta.homework2_todolist.domain.card.exception.CardException;
import com.sparta.homework2_todolist.domain.card.repository.CardRepository;
import com.sparta.homework2_todolist.domain.users.entity.User;
import org.junit.jupiter.api.BeforeEach;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

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
        user1 = User.builder().userId(1L).username("보라돌이")
            .build();

        user2 = User.builder().userId(2L).username("뚜비")
            .build();

        card1 = Card.builder().title("할일 카드").contents("추가 테스트").user(user1)
            .isDone(false).isHidden(false)
            .build();

        card2 = Card.builder().title("제목 제목").contents("내용 내용").user(user2)
            .isDone(false).isHidden(false)
            .build();

        cardRequestDto1 = CardRequestDto.builder()
            .title("할일 카드").contents("추가 테스트")
            .build();

        cardRequestDto2 = CardRequestDto.builder()
            .title("할일 제목 수정").contents("할일 내용 수정")
            .build();
    }

    @DisplayName("할일 카드 추가 테스트")
    @Test
    void addToDo() {
        // given
        given(cardRepository.save(any(Card.class))).willReturn(card1);

        // when
        CardResponseDto result = cardService.addToDo(cardRequestDto1, user1);

        // then
        assertThat(result).extracting("title", "contents")
            .contains("할일 카드", "추가 테스트");

//        assertEquals(cardRequestDto.getTitle(), result.getTitle());
//        assertEquals(cardRequestDto.getContent(), result.getContent());
    }

    @DisplayName("할일 카드 가져오기 테스트")
    @Test
    void getCard() {
        // given
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
        given(cardRepository.findById(anyLong())).willReturn(Optional.of(card2));

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
        given(cardRepository.findById(anyLong())).willReturn(Optional.ofNullable(card2));

        // when
        CardResponseDto result = cardService.concealCard(2L, user2);

        // then
        assertThat(result.getIsHidden()).isEqualTo(true);
    }

    @DisplayName("카드 권한 심사")
    @Test
    void checkAuthority() {
        // when, then
        assertThatThrownBy(() -> cardService.checkAuthority(card1, user2))
            .isInstanceOf(CardException.class).hasMessage(CardErrorCode.NO_AUTHORITY.getMessage());
    }

    @DisplayName("카드 유무 확인")
    @Test
    void getCardEntity() {
        given(cardRepository.findById(anyLong())).willReturn(Optional.empty());
        // when, then
        assertThatThrownBy(() -> cardService.getCardEntity(1L))
            .isInstanceOf(CardException.class).hasMessage(CardErrorCode.NOT_FOUNDED_CARD.getMessage());
    }
}