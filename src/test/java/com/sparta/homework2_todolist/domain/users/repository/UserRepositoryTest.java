package com.sparta.homework2_todolist.domain.users.repository;

import com.sparta.homework2_todolist.domain.users.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @DisplayName("")
    @Test
    void test() {
        // given
        User user = User.builder().userId(1L).
                username("test")
                .password("testpassword")
                .build();
        // when
        userRepository.save(user);
        // then
        User test = userRepository.findByUsername("test").get();
        Assertions.assertThat(test.getUsername()).isEqualTo("test");
    }

}