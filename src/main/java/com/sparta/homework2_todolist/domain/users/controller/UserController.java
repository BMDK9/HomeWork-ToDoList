package com.sparta.homework2_todolist.domain.users.controller;

import com.sparta.homework2_todolist.domain.users.dto.SignupRequestDto;
import com.sparta.homework2_todolist.domain.users.service.UserService;
import com.sparta.homework2_todolist.global.common.CommonCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

        return ResponseEntity.ok(CommonCode.OK.getMessage());
    }
}
