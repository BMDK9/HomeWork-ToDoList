package com.sparta.homework2_todolist.controller;

import com.sparta.homework2_todolist.dto.SignupRequestDto;
import com.sparta.homework2_todolist.response.CommonMsg;
import com.sparta.homework2_todolist.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {

        userService.signup(signupRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(CommonMsg.OK_SIGNUP.getMessage());
    }
}
