package com.example.homework2_todolist.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
