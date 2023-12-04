package com.sparta.homework2_todolist.domain.users.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SignupRequestDtoTest {

    static final String USER_NAME_REG = "^[0-9a-zA-Z]{4,10}+$";
    static final String USER_PASSWORD_REG = "^[0-9a-zA-Z]{8,15}+$";

    @DisplayName("USER_NAME 테스트 성공")
    @Test
    void test1() {
        // given
        String username = "123abcABC";

        // when
        boolean isCorrect = Pattern.matches(USER_NAME_REG, username);

        // then
        assertTrue(isCorrect);
    }

    @DisplayName("USER_PASSWORD 테스트 성공")
    @Test
    void test2() {
        // given
        String userpassword = "abcd1234ABCD";

        // when
        boolean isCorrect = Pattern.matches(USER_PASSWORD_REG, userpassword);

        // then
        assertTrue(isCorrect);

    }
}