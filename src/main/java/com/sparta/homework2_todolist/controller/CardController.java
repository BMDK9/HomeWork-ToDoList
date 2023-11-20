package com.sparta.homework2_todolist.controller;

import com.sparta.homework2_todolist.controller.exception.CardNoAuthorityException;
import com.sparta.homework2_todolist.controller.exception.CardNotFoundException;
import com.sparta.homework2_todolist.dto.CardRequestDto;
import com.sparta.homework2_todolist.dto.CardResponseDto;
import com.sparta.homework2_todolist.dto.exceprion.ErrorResponseDto;
import com.sparta.homework2_todolist.response.CardNomalMsg;
import com.sparta.homework2_todolist.security.UserDetailsImpl;
import com.sparta.homework2_todolist.service.CardService;
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
    public ResponseEntity<String> addToDo(@RequestBody CardRequestDto cardRequestDto,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
// =================================================================== 3(public void) ,  5(public CardResponseDto)
        cardService.addToDo(cardRequestDto, userDetails.getUser());

        return ResponseEntity.status(HttpStatus.CREATED).body(CardNomalMsg.OK_ADDCARD.getMessage());
//===================================================================== 6

    }

    @GetMapping("/{cardId}")
    @ResponseStatus(HttpStatus.OK)
    public CardResponseDto getCard(@PathVariable Long cardId) {

        return cardService.getCard(cardId);
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

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> CardNotFoundExceptionHandler(CardNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), ex.getMessage())
        );
    }

    @ExceptionHandler(CardNoAuthorityException.class)
    public ResponseEntity<ErrorResponseDto> CardNoAuthorityExceptionHandler(CardNoAuthorityException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
            new ErrorResponseDto(HttpStatus.FORBIDDEN.value(), ex.getMessage())
        );
    }
}