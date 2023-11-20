package com.sparta.homework2_todolist.response;

import lombok.Getter;

@Getter
public enum CommonMsg {

    OK_SIGNUP("회원 가입을 완료헀습니다."),
    OK_ADDCARD("카드가 등록되었습니다.");
//    NO_ID("아이디는 알파벳 소문자와 숫자를 이용하여 4~ 10자 이내로 만들어주십시오."),
//    NO_PW("비밀번호는 알파벳 대소문자, 숫자를 이용하여 8 ~ 15자 이내로 만들어주십시오.");

    private final String message;

    CommonMsg(String message) {
        this.message = message;
    }
}
