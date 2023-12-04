package com.sparta.homework2_todolist.domain.users.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SignupRequestDtoTest {

    static final String USER_NAME_REG = "^[0-9a-zA-Z]{4,10}+$";
    static final String USER_PASSWORD_REG = "^[0-9a-zA-Z]{8,15}+$";

    @DisplayName("USER_NAME 테스트 성공")
    @Test
    void test1() {
        // given
        String username = "ABC123abc";

        // when
        boolean isCorrect = Pattern.matches(USER_NAME_REG, username);

        // then
        assertTrue(isCorrect);
    }

    @DisplayName("USER_NAME 길이 실패(미만)")
    @Test
    void test2() {
        // given
        String username = "123";
        // when
        boolean isCorrect = Pattern.matches(USER_NAME_REG, username);

        // then
        assertFalse(isCorrect);
    }

    @DisplayName("USER_NAME 길이 실패(초과)")
    @Test
    void test3() {
        // given
        String username = "12345678901";
        // when
        boolean isCorrect = Pattern.matches(USER_NAME_REG, username);

        // then
        assertFalse(isCorrect);
    }

    @DisplayName("USER_NAME 문자 실패")
    @Test
    void test4() {
        // given
        String username = "A1aㄱ";

        // when
        boolean isCorrect = Pattern.matches(USER_NAME_REG, username);

        // then
        assertFalse(isCorrect);
    }

    @DisplayName("USER_PASSWORD 테스트 성공")
    @Test
    void test5() {
        // given
        String userpassword = "abcd1234ABCD";

        // when
        boolean isCorrect = Pattern.matches(USER_PASSWORD_REG, userpassword);

        // then
        assertTrue(isCorrect);
    }

    @DisplayName("USER_PASSWORD 길이 실패(미만)")
    @Test
    void test6() {
        // given
        String userpassword = "123456";

        // when
        boolean isCorrect = Pattern.matches(USER_PASSWORD_REG, userpassword);

        // then
        assertFalse(isCorrect);
    }

    @DisplayName("USER_PASSWORD 길이 실패(초과)")
    @Test
    void test7() {
        // given
        String userpassword = "1234567890123456";

        // when
        boolean isCorrect = Pattern.matches(USER_PASSWORD_REG, userpassword);

        // then
        assertFalse(isCorrect);
    }

    @DisplayName("USER_PASSWORD 문자 실패")
    @Test
    void test8() {
        // given
        String userpassword = "abc123ABCㄱㄴㄷ";

        // when
        boolean isCorrect = Pattern.matches(USER_PASSWORD_REG, userpassword);

        // then
        assertFalse(isCorrect);
    }

}