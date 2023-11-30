package com.sparta.homework2_todolist.domain.card.exception;

import com.sparta.homework2_todolist.global.exception.ErrorCode;
import com.sparta.homework2_todolist.global.exception.RestApiException;

public class CardException extends RestApiException {

    public CardException(ErrorCode errorCode) {
        super(errorCode);
    }
}
