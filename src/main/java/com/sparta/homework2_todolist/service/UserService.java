package com.sparta.homework2_todolist.service;


import com.sparta.homework2_todolist.entity.UserEntity;
import com.sparta.homework2_todolist.repository.UserRepository;
import com.sparta.homework2_todolist.dto.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        Optional<UserEntity> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        UserEntity userEntity = UserEntity.builder()
            .username(username)
            .password(password)
            .build();

        userRepository.save(userEntity);
    }
}
