package com.sparta.homework2_todolist.domain.card.service;

import com.sparta.homework2_todolist.domain.card.dto.CardRequestDto;
import com.sparta.homework2_todolist.domain.card.dto.CardResponseDto;
import com.sparta.homework2_todolist.domain.card.entity.Card;
import com.sparta.homework2_todolist.domain.card.exception.CardErrorCode;
import com.sparta.homework2_todolist.domain.card.exception.CardException;
import com.sparta.homework2_todolist.domain.card.repository.CardRepository;
import com.sparta.homework2_todolist.domain.users.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CardService {

    private final CardRepository cardRepository;
    //================================================ 9

    @Transactional
    public CardResponseDto addToDo(CardRequestDto cardRequestDto, User user) {
// ==================================================== 4
        //Dto -> entity
        Card saveCard = Card.builder()
            .title(cardRequestDto.getTitle())
            .contents(cardRequestDto.getContent())
            .user(user)
            .isDone(false)
            .isHidden(false)
            .build();

//         =============================================== 8
        cardRepository.save(saveCard);
//        ===================================== 9
        return CardResponseDto.of(saveCard);
//        ===================================== 10
    }

    public CardResponseDto getCard(Long cardId) {
        Card card = getCardEntity(cardId);
        checkHidden(card);
        return CardResponseDto.of(card);
    }


    public List<CardResponseDto> getCards(User user) {

        return cardRepository.findAllByOrderByCreatedAtDesc().stream()
            .filter(isU -> Objects.equals(isU.getUser().getId(), user.getId()) || !isU.isHidden())
            .map(CardResponseDto::of)
            .collect(Collectors.toList());

    }

    @Transactional
    public CardResponseDto updateCard(Long cardId, CardRequestDto cardRequestDto, User user) {
        Card card = getCardEntity(cardId);
        checkAuthority(card, user);
        card.update(cardRequestDto);

        return CardResponseDto.of(card);
    }

    @Transactional
    public CardResponseDto changeCardStatus(Long cardId, User user) {
        Card card = getCardEntity(cardId);
        checkAuthority(card, user);

        card.changeStatus();  // 완료 여부 바꿈

        return CardResponseDto.of(card);
    }

    @Transactional
    public CardResponseDto concealCard(Long cardId, User user) {
        Card card = getCardEntity(cardId);
        checkAuthority(card, user);

        card.hideCard();      // 숨김 여부 바굼

        return CardResponseDto.of(card);
    }

    private void checkHidden(Card card) {

        if (card.isHidden()) {
            throw new CardException(CardErrorCode.HIDDEN_CARDS);
        }
    }

    private void checkAuthority(Card card, User user) {

        if (!card.getUser().getUsername().equals(user.getUsername())) {
            throw new CardException(CardErrorCode.NO_AUTHORITY);
        }
    }

    private Card getCardEntity(Long cardId) {

        return cardRepository.findById(cardId)
            .orElseThrow(() -> new CardException(CardErrorCode.NOT_FOUNDED_CARD));
    }

}
