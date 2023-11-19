package com.sparta.homework2_todolist.controller;

import com.sparta.homework2_todolist.dto.CardRequestDto;
import com.sparta.homework2_todolist.dto.CardResponseDto;
import com.sparta.homework2_todolist.security.UserDetailsImpl;
import com.sparta.homework2_todolist.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {
    // ================================================== 2
    private final CardService cardService;

    // ==================================================== 4
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardResponseDto addToDo(@RequestBody CardRequestDto cardRequestDto,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
// =================================================================== 3(public void) ,  5(public CardResponseDto)
        CardResponseDto cardResponseDto = cardService.addToDo(cardRequestDto, userDetails.getUser());
        return cardResponseDto;
//===================================================================== 6

    }

    @GetMapping("/{cardId}")
    public CardResponseDto getCard (@PathVariable Long cardId,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.getCard(cardId, userDetails.getUser());
    }

    @GetMapping()
    public List<CardResponseDto> getCards (@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.getCards(userDetails.getUser());
    }

    @PatchMapping("/{cardId}")
    public CardResponseDto updateCard(@PathVariable Long cardId,
                                      @RequestBody CardRequestDto cardRequestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.updateCard(cardId, cardRequestDto, userDetails.getUser());
    }
}
