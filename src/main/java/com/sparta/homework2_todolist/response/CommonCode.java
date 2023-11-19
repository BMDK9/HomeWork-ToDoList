package com.sparta.homework2_todolist.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CommonCode {

    OK("회원 가입을 완료헀습니다.", HttpStatus.OK.value());
//    IDNO("아이디는 알파벳 소문자와 숫자를 이용하여 4~ 10자 이내로 만들어주십시오.",
//        HttpStatus.INTERNAL_SERVER_ERROR.value()),
//    PWNO("비밀번호는 알파벳 대소문자, 숫자를 이용하여 8 ~ 15자 이내로 만들어주십시오.",
//        HttpStatus.INTERNAL_SERVER_ERROR.value());

    private final String message;

    private final Integer code;

    CommonCode(String message, Integer code) {
        this.message = message;
        this.code = code;
    }
}
