package com.sparta.homework2_todolist.global.common;

import com.sparta.homework2_todolist.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonCode implements ErrorCode {

    OK(HttpStatus.OK, "성공");

    private final HttpStatus httpStatus;
    private final String message;

}
