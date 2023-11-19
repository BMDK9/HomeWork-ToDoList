package com.example.homework2_todolist.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDto {
    private String username;
    private String password;
}