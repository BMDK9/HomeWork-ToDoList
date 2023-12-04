package com.sparta.homework2_todolist.domain.card.service;

import com.sparta.homework2_todolist.domain.card.dto.CardRequestDto;
import com.sparta.homework2_todolist.domain.card.dto.CardResponseDto;
import com.sparta.homework2_todolist.domain.card.entity.Card;
import com.sparta.homework2_todolist.domain.card.exception.CardErrorCode;
import com.sparta.homework2_todolist.domain.card.exception.CardException;
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

import static com.sparta.homework2_todolist.domain.card.constant.DoneConstant.DEFAULT_DONE;
import static com.sparta.homework2_todolist.domain.card.constant.HiddenConstant.DEFAULT_HIDDEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @DisplayName("성공 : 할일 카드 추가하기")
    @Test
    void test1() {
        // given
        User user1 = User.builder().userId(1L).username("보라돌이")
            .build();

        CardRequestDto cardRequestDto = CardRequestDto.builder()
            .title("1111").contents("aaaa")
            .build();

        Card card1 = Card.builder().id(1L).title("1111").contents("aaaa").user(user1)
            .isDone(false).isHidden(false)
            .build();

        given(cardRepository.save(any(Card.class))).willReturn(card1);

        // when
        CardResponseDto result = cardService.addToDo(cardRequestDto, user1);

        // then
        assertThat(result).extracting("title", "contents", "isDone", "isHidden")
            .contains("1111", "aaaa", false, false);

        assertEquals(cardRequestDto.getTitle(), result.getTitle());
        assertEquals(cardRequestDto.getContents(), result.getContents());
        assertEquals(DEFAULT_DONE,result.getIsDone());
        assertEquals(DEFAULT_HIDDEN,result.getIsHidden());
    }

    @DisplayName("성공 : 할일 카드 가져오기")
    @Test
    void test2() {
        // given
        User user1 = User.builder().userId(1L).username("보라돌이")
            .build();

        User user2 = User.builder().userId(2L).username("뚜비")
            .build();

        Card card2 = Card.builder().id(2L).title("2222").contents("bbbb").user(user2)
            .isDone(false).isHidden(false)
            .build();

        given(cardRepository.findById(anyLong())).willReturn(Optional.of(card2));

        // when
        CardResponseDto card = cardService.getCard(2L, user1);

        // then
        assertThat(card).extracting("title", "contents", "isDone", "isHidden")
            .contains("2222", "bbbb", false, false);
    }

    @DisplayName("실패 : 없는 카드 가져오기")
    @Test
    void test3() {
        // given
        User user1 = User.builder().userId(1L).username("보라돌이")
            .build();

        given(cardRepository.findById(anyLong())).willReturn(Optional.empty());

        // when then
        assertThatThrownBy(() -> cardService.getCard(1L, user1)).isInstanceOf(
            CardException.class).hasMessage(CardErrorCode.NOT_FOUNDED_CARD.getMessage());
    }

    @DisplayName("실패 : 권한 없이 비공개 카드 가져오기")
    @Test
    void test4() {
        // given
        User user1 = User.builder().userId(1L).username("보라돌이")
            .build();

        User user2 = User.builder().userId(2L).username("뚜비")
            .build();

        Card card1 = Card.builder().id(1L).title("1111").contents("aaaa").user(user1)
            .isDone(false).isHidden(true)
            .build();

        given(cardRepository.findById(anyLong())).willReturn(Optional.ofNullable(card1));

        // when then
        assertThatThrownBy(() -> cardService.getCard(1L, user2)).isInstanceOf(
            CardException.class).hasMessage(CardErrorCode.HIDDEN_CARDS.getMessage());
    }

    @DisplayName("성공 : 할일 카드 목록 조회(비공개 없음)")
    @Test
    void test5() {
        // given
        User user1 = User.builder().userId(1L).username("보라돌이")
            .build();

        User user2 = User.builder().userId(2L).username("뚜비")
            .build();

        Card card1 = Card.builder().id(1L).title("1111").contents("aaaa").user(user1)
            .isDone(false).isHidden(false)
            .build();

        Card card2 = Card.builder().id(2L).title("2222").contents("bbbb").user(user2)
            .isDone(false).isHidden(false)
            .build();

        given(cardRepository.findAllByOrderByCreatedAtDesc()).willReturn(List.of(card1, card2));

        // when
        List<CardResponseDto> cards = cardService.getCards(user1);

        // then
        assertThat(cards).hasSize(2);
        assertThat(cards).extracting("title", "contents", "isDone", "isHidden")
            .contains(
                tuple("1111", "aaaa", false, false),
                tuple("2222", "bbbb", false, false));
    }

    @DisplayName("성공 : 할일 카드 목록 조회(비공개 제외)")
    @Test
    void test6() {
        // given
        User user1 = User.builder().userId(1L).username("보라돌이")
            .build();

        User user2 = User.builder().userId(2L).username("뚜비")
            .build();

        Card card1 = Card.builder().id(1L).title("1111").contents("aaaa").user(user1)
            .isDone(false).isHidden(true)
            .build();

        Card card2 = Card.builder().id(2L).title("2222").contents("bbbb").user(user2)
            .isDone(false).isHidden(false)
            .build();

        given(cardRepository.findAllByOrderByCreatedAtDesc()).willReturn(List.of(card1, card2));

        // when
        List<CardResponseDto> cards = cardService.getCards(user2);

        // then
        assertThat(cards).hasSize(1);
        assertThat(cards).extracting("title", "contents", "isDone", "isHidden")
            .contains(
                tuple("2222", "bbbb", false, false));
    }

    @DisplayName("성공 : 비공개 당사자가 할일 카드 목록 조회(비공개 포함)")
    @Test
    void test7() {
        // given
        User user1 = User.builder().userId(1L).username("보라돌이")
            .build();

        User user2 = User.builder().userId(2L).username("뚜비")
            .build();

        Card card1 = Card.builder().id(1L).title("1111").contents("aaaa").user(user1)
            .isDone(false).isHidden(true)
            .build();

        Card card2 = Card.builder().id(2L).title("2222").contents("bbbb").user(user2)
            .isDone(false).isHidden(true)
            .build();

        given(cardRepository.findAllByOrderByCreatedAtDesc()).willReturn(List.of(card1, card2));

        // when
        List<CardResponseDto> cards = cardService.getCards(user1);

        // then
        assertThat(cards).hasSize(1);
        assertThat(cards).extracting("title", "contents", "isDone", "isHidden")
            .contains(tuple("1111", "aaaa", false, true));
    }

    @DisplayName("성공 : 제 3자가 할일 카드 목록 조회(비공개 제외)")
    @Test
    void test8() {
        // given
        User user1 = User.builder().userId(1L).username("보라돌이")
            .build();

        User user2 = User.builder().userId(2L).username("뚜비")
            .build();

        User user3 = User.builder().userId(3L).username("나나")
            .build();

        Card card1 = Card.builder().id(1L).title("1111").contents("aaaa").user(user1)
            .isDone(false).isHidden(true)
            .build();

        Card card2 = Card.builder().id(2L).title("2222").contents("bbbb").user(user2)
            .isDone(false).isHidden(true)
            .build();

        given(cardRepository.findAllByOrderByCreatedAtDesc()).willReturn(List.of(card1, card2));

        // when
        List<CardResponseDto> cards = cardService.getCards(user3);

        // then
        assertThat(cards).hasSize(0);
    }

    @DisplayName("성공 : 본인의 카드 수정")
    @Test
    void test9() {
        // given
        User user2 = User.builder().userId(2L).username("뚜비")
            .build();

        Card card2 = Card.builder().id(2L).title("2222").contents("bbbb").user(user2)
            .isDone(false).isHidden(false)
            .build();

        CardRequestDto cardRequestDto2 = CardRequestDto.builder()
            .title("2a2a").contents("2b2b")
            .build();

        given(cardRepository.findById(anyLong())).willReturn(Optional.of(card2));

        // when
        CardResponseDto result = cardService.updateCard(2L, cardRequestDto2, user2);

        // then
        assertThat(result).extracting("title", "contents")
            .contains("2a2a", "2b2b");
    }

    @DisplayName("실패 : 타인의 카드를 수정")
    @Test
    void test10() {
        // given
        User user1 = User.builder().userId(1L).username("보라돌이")
            .build();

        User user2 = User.builder().userId(2L).username("뚜비")
            .build();

        Card card2 = Card.builder().id(2L).title("2222").contents("bbbb").user(user2)
            .isDone(false).isHidden(false)
            .build();

        CardRequestDto cardRequestDto2 = CardRequestDto.builder()
            .title("2b2b").contents("2b2b")
            .build();

        given(cardRepository.findById(anyLong())).willReturn(Optional.of(card2));

        // when then
        assertThatThrownBy(() -> cardService.updateCard(2L, cardRequestDto2, user1))
            .isInstanceOf(CardException.class).hasMessage(CardErrorCode.NO_AUTHORITY.getMessage());
    }

    @DisplayName("성공 : 본인 할일 카드 삭제")
    @Test
    void test11() {
        // given
        User user2 = User.builder().userId(2L).username("뚜비")
            .build();

        Card card2 = Card.builder().id(2L).title("제목 제목").contents("내용 내용").user(user2)
            .isDone(false).isHidden(false)
            .build();

        given(cardRepository.findById(anyLong())).willReturn(Optional.of(card2));

        // when
        cardService.deleteCard(1L, user2);

        // then
        Mockito.verify(cardRepository, Mockito.times(1)).delete(any(Card.class));
    }

    @DisplayName("실패 : 타인 할일 카드 삭제")
    @Test
    void test12() {
        // given
        User user1 = User.builder().userId(1L).username("보라돌이")
            .build();

        User user2 = User.builder().userId(2L).username("뚜비")
            .build();

        Card card2 = Card.builder().id(2L).title("2222").contents("bbbb").user(user2)
            .isDone(false).isHidden(false)
            .build();

        given(cardRepository.findById(anyLong())).willReturn(Optional.of(card2));

        // when then
        assertThatThrownBy(() -> cardService.deleteCard(2L, user1))
            .isInstanceOf(CardException.class).hasMessage(CardErrorCode.NO_AUTHORITY.getMessage());
    }

    @DisplayName("성공 : 본인 카드 완료 처리")
    @Test
    void test13() {
        // given
        User user1 = User.builder().userId(1L).username("보라돌이")
            .build();

        Card card1 = Card.builder().id(1L).title("1111").contents("aaaa").user(user1)
            .isDone(false).isHidden(false)
            .build();

        given(cardRepository.findById(anyLong())).willReturn(Optional.ofNullable(card1));

        // when
        CardResponseDto result = cardService.changeCardStatus(1L, user1);

        // then
        assertThat(result.getIsDone()).isEqualTo(true);
    }

    @DisplayName("실패 : 타인 카드 완료 처리")
    @Test
    void test14() {
        // given
        User user1 = User.builder().userId(1L).username("보라돌이")
            .build();

        User user2 = User.builder().userId(2L).username("뚜비")
            .build();

        Card card1 = Card.builder().id(1L).title("1111").contents("aaaa").user(user1)
            .isDone(false).isHidden(false)
            .build();

        given(cardRepository.findById(anyLong())).willReturn(Optional.ofNullable(card1));

        // when then
        assertThatThrownBy(() -> cardService.changeCardStatus(1L, user2))
            .isInstanceOf(CardException.class).hasMessage(CardErrorCode.NO_AUTHORITY.getMessage());
    }

    @DisplayName("성공 : 본인 카드 숨김 처리")
    @Test
    void test15() {
        // given
        User user1 = User.builder().userId(1L).username("보라돌이")
            .build();

        Card card1 = Card.builder().id(1L).title("1111").contents("aaaa").user(user1)
            .isDone(false).isHidden(false)
            .build();

        given(cardRepository.findById(anyLong())).willReturn(Optional.ofNullable(card1));

        // when
        CardResponseDto result = cardService.concealCard(1L, user1);

        // then
        assertThat(result.getIsHidden()).isEqualTo(true);
    }

    @DisplayName("실패 : 타인 카드 완료 처리")
    @Test
    void test16() {
        // given
        User user1 = User.builder().userId(1L).username("보라돌이")
            .build();

        User user2 = User.builder().userId(2L).username("뚜비")
            .build();

        Card card1 = Card.builder().id(1L).title("1111").contents("aaaa").user(user1)
            .isDone(false).isHidden(false)
            .build();

        given(cardRepository.findById(anyLong())).willReturn(Optional.ofNullable(card1));

        // when then
        assertThatThrownBy(() -> cardService.concealCard(1L, user2))
            .isInstanceOf(CardException.class).hasMessage(CardErrorCode.NO_AUTHORITY.getMessage());
    }
}