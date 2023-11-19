package com.example.homework2_todolist.service;

import com.example.homework2_todolist.dto.CardRequestDto;
import com.example.homework2_todolist.dto.CardResponseDto;
import com.example.homework2_todolist.entity.CardEntity;
import com.example.homework2_todolist.repository.CardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardJpaRepository cardJpaRepository;
    //================================================ 9

    public CardResponseDto addToDo(CardRequestDto cardRequestDto) {
// ==================================================== 4
        //Dto -> entity
        CardEntity cardEntity = new CardEntity(cardRequestDto);
//         =============================================== 8
        CardEntity savePost = cardJpaRepository.save(cardEntity);
//        ===================================== 9
        return new CardResponseDto(savePost);
//        ===================================== 10
    }

    public CardResponseDto getCard(Long cardId) {
        CardEntity cardEntity = getCardEntity(cardId);

        return new CardResponseDto(cardEntity);
    }


    public List<CardResponseDto> getCard() {
        return cardJpaRepository.findAllByOrderByCreatedAtDesc().stream()
            .map(CardResponseDto::new)
            .collect(Collectors.toList());
    }

    @Transactional
    public CardResponseDto updateCard(Long cardId, CardRequestDto cardRequestDto) {
        CardEntity cardEntity = getCardEntity(cardId);

//      비밀번호가 아니라 토큰검사로? ID로?
//        if (!cardEntity.getPassword().equals(cardRequestDto.getPassword())) {
//            throw new NullPointerException("비밀번호가 일치하지 않습니다.");
//        }
//        cardEntity.update(cardRequestDto);

        return new CardResponseDto(cardEntity);
    }
    private CardEntity getCardEntity(Long cardId) {
        return cardJpaRepository.findById(cardId)
            .orElseThrow(() -> new NullPointerException("해당 카드는 없습니다."));
    }
}
