package com.sparta.homework2_todolist.domain.users.exception;

import com.sparta.homework2_todolist.global.exception.ErrorCode;
import com.sparta.homework2_todolist.global.exception.RestApiException;

public class UserException extends RestApiException {


    public UserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
