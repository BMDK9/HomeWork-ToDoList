package com.sparta.homework2_todolist.domain.card.exception;

import com.sparta.homework2_todolist.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CardErrorCode implements ErrorCode {

    NOT_FOUNDED_CARD(HttpStatus.NOT_FOUND, "존재하지 않는 카드입니다."),
    NO_AUTHORITY(HttpStatus.FORBIDDEN, "해당 권한이 없습니다."),
    HIDDEN_CARDS(HttpStatus.OK, "비공개 설정된 카드입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
