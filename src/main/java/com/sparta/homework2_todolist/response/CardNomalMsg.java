package com.sparta.homework2_todolist.response;

import lombok.Getter;

@Getter
public enum CardNomalMsg {

    OK_SIGNUP("회원 가입을 완료헀습니다."),
    OK_ADDCARD("카드가 등록되었습니다.");

    private final String message;

    CardNomalMsg(String message) {
        this.message = message;
    }
}
