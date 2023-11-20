package com.sparta.homework2_todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    @NotBlank
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9]).{4,10}$")
    private String username;

    @NotBlank
//    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,15}$")
    private String password;
}
