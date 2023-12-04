package com.sparta.homework2_todolist.domain.users.controller;

import com.sparta.homework2_todolist.ControllerTestSupport;
import com.sparta.homework2_todolist.common.mvc.MockSpringSecurityFilter;
import com.sparta.homework2_todolist.domain.users.dto.SignupRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends ControllerTestSupport {

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .apply(springSecurity(new MockSpringSecurityFilter()))
            .alwaysDo(print())
            .build();
    }

    @DisplayName("회원 가입")
    @Test
    void test() throws Exception {
        // given
        SignupRequestDto signupRequestDto = SignupRequestDto.builder()
            .username("testUser").password("ABC123abc").build();

        // when then
        mockMvc.perform(
            post("/api/users/signup")
                .content(objectMapper.writeValueAsString(signupRequestDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

    }
}