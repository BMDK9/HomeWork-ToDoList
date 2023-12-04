package com.sparta.homework2_todolist.domain.card.controller;

import com.sparta.homework2_todolist.ControllerTestSupport;
import com.sparta.homework2_todolist.common.mvc.MockSpringSecurityFilter;
import com.sparta.homework2_todolist.domain.card.dto.CardRequestDto;
import com.sparta.homework2_todolist.domain.card.entity.Card;
import com.sparta.homework2_todolist.domain.users.entity.User;
import com.sparta.homework2_todolist.global.security.UserDetailsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CardControllerTest extends ControllerTestSupport {

    private void mockUserSetup() {
        User testUser = User.builder()
            .username("test1").password("ABC123abc").build();

        UserDetailsImpl testUserDetails = new UserDetailsImpl(testUser);
        mockPrincipal = new UsernamePasswordAuthenticationToken(testUserDetails, "",
            testUserDetails.getAuthorities());
    }

    @BeforeEach
    public void setup() {
        this.mockUserSetup();

        mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .apply(springSecurity(new MockSpringSecurityFilter()))
            .alwaysDo(print())
            .build();
    }

    @DisplayName("할일 카드를 생성한다")
    @Test
    void test1() throws Exception {
        // given
        CardRequestDto createCard = CardRequestDto.builder()
            .title("생성된 게시글").contents("할일 카드 생성입니다.").build();

        // when then
        mockMvc.perform(
            post("/api/cards")
                .content(objectMapper.writeValueAsString(createCard))
                .contentType(MediaType.APPLICATION_JSON)
                .principal(mockPrincipal)
        ).andExpect(status().isOk());
    }

    @DisplayName("할일 카드를 조회한다.")
    @Test
    void test2() throws Exception {
        // given
        Card card = Card.builder().id(1L).build();

        // when then
        mockMvc.perform(
            get("/api/cards/" + card.getId())
                .principal(mockPrincipal)
        ).andExpect(status().isOk());
    }

    @DisplayName("전체 할일 카드를 조회한다.")
    @Test
    void test3() throws Exception {
        // when then
        mockMvc.perform(
            get("/api/cards")
                .principal(mockPrincipal)
        ).andExpect(status().isOk());
    }

    @DisplayName("할일 카드를 수정한다,")
    @Test
    void test4() throws Exception {
        // given
        Card card = Card.builder().id(1L).build();

        CardRequestDto updateCard = CardRequestDto.builder()
            .title("할일 카드 수정")
            .contents("수정합니다~")
            .build();

        // when then
        mockMvc.perform(
            get("/api/cards/" + card.getId())
                .content(objectMapper.writeValueAsString(updateCard))
                .principal(mockPrincipal)
        ).andExpect(status().isOk());
    }

    @DisplayName("할일 카드 삭제하기")
    @Test
    void test5() throws Exception {
        // given
        Card card = Card.builder().id(1L).build();

        // when then
        mockMvc.perform(
            delete("/api/cards/" + card.getId())
                .principal(mockPrincipal)
        ).andExpect(status().isNoContent());
    }

    @DisplayName("할일 카드 완료상태를 변경한다.")
    @Test
    void test6() throws Exception {
        // given
        Card card = Card.builder().id(1L).build();

        // when then
        mockMvc.perform(
            patch("/api/cards/change/" + card.getId())
                .principal(mockPrincipal)
        ).andExpect(status().isAccepted());
    }

    @DisplayName("할일 카드 완료상태를 변경한다.")
    @Test
    void test7() throws Exception {
        // given
        Card card = Card.builder().id(1L).build();

        // when then
        mockMvc.perform(
            patch("/api/cards/hide/" + card.getId())
                .principal(mockPrincipal)
        ).andExpect(status().isAccepted());
    }
}