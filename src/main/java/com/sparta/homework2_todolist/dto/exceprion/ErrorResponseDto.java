package com.sparta.homework2_todolist.dto.exceprion;

import lombok.Getter;

@Getter
public class ErrorResponseDto {
// 이거 못 알아 먹음
    private final Error error;

    public ErrorResponseDto(int status, String msg) {
        this.error = new Error(status, msg);
    }

    record Error(
        int status,
        String msg
    ) {
    }
}
