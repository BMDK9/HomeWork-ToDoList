package com.sparta.homework2_todolist.domain.users.service;


import com.sparta.homework2_todolist.domain.users.dto.SignupRequestDto;
import com.sparta.homework2_todolist.domain.users.entity.User;
import com.sparta.homework2_todolist.domain.users.exception.UserErrorCode;
import com.sparta.homework2_todolist.domain.users.exception.UserException;
import com.sparta.homework2_todolist.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {

        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new UserException(UserErrorCode.ALREADY_EXSIST_USER);
        }

        User user = User.builder()
            .username(username)
            .password(password)
            .build();

        userRepository.save(user);
    }
}
