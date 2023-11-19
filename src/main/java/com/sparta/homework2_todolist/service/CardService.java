package com.sparta.homework2_todolist.service;

import com.sparta.homework2_todolist.dto.CardRequestDto;
import com.sparta.homework2_todolist.dto.CardResponseDto;
import com.sparta.homework2_todolist.entity.CardEntity;
import com.sparta.homework2_todolist.entity.UserEntity;
import com.sparta.homework2_todolist.repository.CardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    //================================================ 9

    public CardResponseDto addToDo(CardRequestDto cardRequestDto, UserEntity userEntity) {
// ==================================================== 4
        //Dto -> entity
        CardEntity cardEntity = new CardEntity(cardRequestDto, userEntity);
//         =============================================== 8
        CardEntity savePost = cardRepository.save(cardEntity);
//        ===================================== 9
        return new CardResponseDto(savePost);
//        ===================================== 10
    }

    public CardResponseDto getCard(Long cardId) {
        CardEntity cardEntity = getCardEntity(cardId);
        return new CardResponseDto(cardEntity);
    }


    public List<CardResponseDto> getCards() {
        return cardRepository.findAllByOrderByCreatedAtDesc().stream()
            .map(CardResponseDto::new)
            .collect(Collectors.toList());
    }

    @Transactional
    public CardResponseDto updateCard(Long cardId, CardRequestDto cardRequestDto, UserEntity userEntity) {
        CardEntity cardEntity = checkAuthority(cardId, userEntity);

        cardEntity.update(cardRequestDto);

        return new CardResponseDto(cardEntity);
    }

    @Transactional
    public CardResponseDto changeCardStatus(Long cardId, UserEntity userEntity) {
        CardEntity cardEntity = checkAuthority(cardId, userEntity);

        cardEntity.changeStatus();

        return new CardResponseDto(cardEntity);
    }

    private CardEntity checkAuthority(Long cardId, UserEntity userEntity) {
        CardEntity cardEntity = getCardEntity(cardId);

        if (!cardEntity.getUserEntity().getUsername().equals(userEntity.getUsername())) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }
        return cardEntity;
    }

    private CardEntity getCardEntity(Long cardId) {
        return cardRepository.findById(cardId)
            .orElseThrow(() -> new NullPointerException("해당 카드는 없습니다."));
    }
}
