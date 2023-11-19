package com.sparta.homework2_todolist.controller;

import com.sparta.homework2_todolist.dto.CardRequestDto;
import com.sparta.homework2_todolist.dto.CardResponseDto;
import com.sparta.homework2_todolist.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/cards")
@RequiredArgsConstructor
public class CardController {
    // ================================================== 2
    private final CardService cardService;

    // ==================================================== 4
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardResponseDto addToDo(@RequestBody CardRequestDto cardRequestDto) {
// =================================================================== 3(public void) ,  5(public CardResponseDto)
        CardResponseDto cardResponseDto = cardService.addToDo(cardRequestDto);
        return cardResponseDto;
//===================================================================== 6

    }

    @GetMapping("/{cardId}")
    public CardResponseDto getCard (@PathVariable Long cardId) {
        return cardService.getCard(cardId);
    }

    @GetMapping()
    public List<CardResponseDto> getPosts () {
        return cardService.getCard();
    }

    @PatchMapping("/{cardId}")
    public CardResponseDto updateCard(@PathVariable Long cardId,
                                      @RequestBody CardRequestDto cardRequestDto) {
        return cardService.updateCard(cardId, cardRequestDto);
    }
}
