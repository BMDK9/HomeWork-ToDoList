package com.sparta.homework2_todolist;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.homework2_todolist.domain.card.controller.CardController;
import com.sparta.homework2_todolist.domain.card.service.CardService;
import com.sparta.homework2_todolist.domain.users.controller.UserController;
import com.sparta.homework2_todolist.domain.users.service.UserService;
import com.sparta.homework2_todolist.global.config.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;

@WebMvcTest(controllers =
    {CardController.class,
     UserController.class
    }, excludeFilters = {
    @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = WebSecurityConfig.class
    )
})
public abstract class ControllerTestSupport {

    @MockBean
    protected UserService userService;

    @MockBean
    protected CardService cardService;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mockMvc;

    protected Principal mockPrincipal;
}
