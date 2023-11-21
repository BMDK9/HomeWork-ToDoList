package com.sparta.homework2_todolist.service;

import com.sparta.homework2_todolist.controller.exception.CardNoAuthorityException;
import com.sparta.homework2_todolist.controller.exception.CardNotFoundException;
import com.sparta.homework2_todolist.dto.CardRequestDto;
import com.sparta.homework2_todolist.dto.CardResponseDto;
import com.sparta.homework2_todolist.entity.Card;
import com.sparta.homework2_todolist.entity.User;
import com.sparta.homework2_todolist.repository.CardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    //================================================ 9

    public CardResponseDto addToDo(CardRequestDto cardRequestDto, User user) {
// ==================================================== 4
        //Dto -> entity
        Card card = new Card(cardRequestDto, user);
//         =============================================== 8
        Card saveCard = cardRepository.save(card);
//        ===================================== 9
        return new CardResponseDto(saveCard);
//        ===================================== 10
    }

    public CardResponseDto getCard(Long cardId) {
        Card card = getCardEntity(cardId);
        return new CardResponseDto(card);
    }


    public List<CardResponseDto> getCards(User user) {

        return cardRepository.findAllByOrderByCreatedAtDesc().stream()
            .filter(isU -> Objects.equals(isU.getUser().getId(), user.getId()) || !isU.isHidden())
            .map(CardResponseDto::new)
            .collect(Collectors.toList());

    }

    @Transactional
    public CardResponseDto updateCard(Long cardId, CardRequestDto cardRequestDto, User user) {
        Card card = checkAuthority(cardId, user);

        card.update(cardRequestDto);

        return new CardResponseDto(card);
    }

    @Transactional
    public CardResponseDto changeCardStatus(Long cardId, User user) {
        Card card = checkAuthority(cardId, user);

        card.changeStatus();  // 완료 여부 바꿈

        return new CardResponseDto(card);
    }

    @Transactional
    public CardResponseDto concealCard(Long cardId, User user) {
        Card card = checkAuthority(cardId, user);

        card.hideCard();      // 숨김 여부 바굼

        return new CardResponseDto(card);
    }

    private Card checkAuthority(Long cardId, User user) {
        Card card = getCardEntity(cardId);

        if (!card.getUser().getUsername().equals(user.getUsername())) {
            throw new CardNoAuthorityException("수정 권한이 없습니다.");
        }
        return card;
    }

    private Card getCardEntity(Long cardId) {
        return cardRepository.findById(cardId)
            .orElseThrow(() -> new CardNotFoundException("해당 카드는 없습니다."));
    }

}
