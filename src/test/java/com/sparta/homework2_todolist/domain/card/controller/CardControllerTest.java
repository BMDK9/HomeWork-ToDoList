package com.sparta.homework2_todolist.domain.card.controller;

import com.sparta.homework2_todolist.ControllerTestSupport;
import com.sparta.homework2_todolist.common.mvc.MockSpringSecurityFilter;
import com.sparta.homework2_todolist.domain.users.entity.User;
import com.sparta.homework2_todolist.global.security.UserDetailsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
class CardControllerTest extends ControllerTestSupport {

    private void mockUserSetup() {
        User testUser = User.builder()
            .userId(1L)
            .username("test1")
            .password("ABC123abc")
            .build();

        UserDetailsImpl testUserDetails = new UserDetailsImpl(testUser);
        mockPrincipal = new UsernamePasswordAuthenticationToken(testUserDetails, "",
            testUserDetails.getAuthorities());
    }

    @BeforeEach
    private void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .apply(springSecurity(new MockSpringSecurityFilter()))
            .alwaysDo(print())
            .build();
    }

}