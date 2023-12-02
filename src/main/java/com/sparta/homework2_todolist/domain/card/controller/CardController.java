package com.sparta.homework2_todolist.domain.card.controller;

import com.sparta.homework2_todolist.domain.card.dto.CardRequestDto;
import com.sparta.homework2_todolist.domain.card.dto.CardResponseDto;
import com.sparta.homework2_todolist.domain.card.service.CardService;
import com.sparta.homework2_todolist.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CardResponseDto> addToDo(@RequestBody CardRequestDto cardRequestDto,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
// =================================================================== 3(public void) ,  5(public CardResponseDto)
        return ResponseEntity.ok(cardService.addToDo(cardRequestDto, userDetails.getUser()));

//===================================================================== 6

    }

    @GetMapping("/{cardId}")
    public CardResponseDto getCard(@PathVariable Long cardId,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return cardService.getCard(cardId, userDetails.getUser());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CardResponseDto> getCards(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.getCards(userDetails.getUser());
    }

    @PatchMapping("/{cardId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CardResponseDto updateCard(@PathVariable Long cardId,
                                      @RequestBody CardRequestDto cardRequestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.updateCard(cardId, cardRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/{cardId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteCard(@PathVariable Long cardId,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        cardService.deleteCard(cardId, userDetails.getUser());

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/change/{cardId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CardResponseDto changeCardStatus(@PathVariable Long cardId,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.changeCardStatus(cardId, userDetails.getUser());
    }

    @PatchMapping("/hide/{cardId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CardResponseDto concealCard(@PathVariable Long cardId,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.concealCard(cardId, userDetails.getUser());
    }
}