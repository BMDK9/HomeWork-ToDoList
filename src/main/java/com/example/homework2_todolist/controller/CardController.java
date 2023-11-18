package com.example.homework2_todolist.controller;

import com.example.homework2_todolist.dto.RequestDto;
import com.example.homework2_todolist.dto.ResponseDto;
import com.example.homework2_todolist.service.CardService;
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
    public ResponseDto addToDo(@RequestBody RequestDto requestDto) {
// =================================================================== 3(public void) ,  5(public ResponseDto)
        ResponseDto responseDto = cardService.addToDo(requestDto);
        return responseDto;
//===================================================================== 6

    }

    @GetMapping("/{cardId}")
    public ResponseDto getCard (@PathVariable Long cardId) {
        return cardService.getCard(cardId);
    }

    @GetMapping()
    public List<ResponseDto> getPosts () {
        return cardService.getCard();
    }

    @PatchMapping("/{cardId}")
    public ResponseDto updateCard(@PathVariable Long cardId,
                                  @RequestBody RequestDto requestDto) {
        return cardService.updateCard(cardId, requestDto);
    }
}
