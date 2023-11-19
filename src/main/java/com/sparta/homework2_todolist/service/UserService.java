package com.sparta.homework2_todolist.service;


import com.sparta.homework2_todolist.dto.SignupRequestDto;
import com.sparta.homework2_todolist.entity.UserEntity;
import com.sparta.homework2_todolist.repository.UserRepository;
import com.sparta.homework2_todolist.response.CommonCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupRequestDto signupRequestDto) {

        if (!signupRequestDto.getUsername().matches("^(?=.*[a-z])(?=.*[0-9]).{4,10}$")) {
            throw new IllegalArgumentException("아이디는 알파벳 소문자와 숫자를 이용하여 4~ 10자 이내로 만들어주십시오.");
        }

        if (!signupRequestDto.getPassword().matches("^(?=.*[a-zA-Z])(?=.*[0-9]).{8,15}$")) {
            throw new IllegalArgumentException("비밀번호는 알파벳 대소문자, 숫자를 이용하여 8 ~ 15자 이내로 만들어주십시오.");
        }

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
