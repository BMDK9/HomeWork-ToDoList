package com.example.homework2_todolist.service;

import com.example.homework2_todolist.dto.RequestDto;
import com.example.homework2_todolist.dto.ResponseDto;
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

    public ResponseDto addToDo(RequestDto requestDto) {
// ==================================================== 4
        //Dto -> entity
        CardEntity cardEntity = new CardEntity(requestDto);
//         =============================================== 8
        CardEntity savePost = cardJpaRepository.save(cardEntity);
//        ===================================== 9
        return new ResponseDto(savePost);
//        ===================================== 10
    }

    public ResponseDto getCard(Long cardId) {
        CardEntity cardEntity = getCardEntity(cardId);

        return new ResponseDto(cardEntity);
    }


    public List<ResponseDto> getCard() {
        return cardJpaRepository.findAllByOrderByCreatedAtDesc().stream()
            .map(ResponseDto::new)
            .collect(Collectors.toList());
    }

    @Transactional
    public ResponseDto updateCard(Long cardId, RequestDto requestDto) {
        CardEntity cardEntity = getCardEntity(cardId);

        if (!cardEntity.getPassword().equals(requestDto.getPassword())) {
            throw new NullPointerException("비밀번호가 일치하지 않습니다.");
        }
        cardEntity.update(requestDto);

        return new ResponseDto(cardEntity);
    }
    private CardEntity getCardEntity(Long cardId) {
        return cardJpaRepository.findById(cardId)
            .orElseThrow(() -> new NullPointerException("해당 카드는 없습니다."));
    }
}
