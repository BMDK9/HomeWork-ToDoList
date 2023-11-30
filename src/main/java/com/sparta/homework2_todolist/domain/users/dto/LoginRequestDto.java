package com.sparta.homework2_todolist.domain.users.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String username;
    private String password;
}