package com.sparta.homework2_todolist.controller;

import com.sparta.homework2_todolist.controller.exception.SignupInputException;
import com.sparta.homework2_todolist.dto.SignupRequestDto;
import com.sparta.homework2_todolist.dto.exceprion.ErrorResponseDto;
import com.sparta.homework2_todolist.response.CardNomalMsg;
import com.sparta.homework2_todolist.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {

        userService.signup(signupRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(CardNomalMsg.OK_SIGNUP.getMessage());
    }

    @ExceptionHandler(SignupInputException.class)
    public ResponseEntity<ErrorResponseDto> SignupInputException(SignupInputException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage())
        );
    }
}
