package com.sparta.homework2_todolist.controller;

import com.sparta.homework2_todolist.service.UserService;
import com.sparta.homework2_todolist.dto.SignupRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1.0/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@Valid SignupRequestDto signupRequestDto, BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        //  Validation 예외 처리 (안 배운 것임)
        if (fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + "필드 : " + fieldError.getDefaultMessage());
            }
            return "redirect:/api/v1.0/user/signup";
        }
        userService.signup(signupRequestDto);

        return "redirect:/api/v1.0/login";
    }

    // 관리자인지 확인하는거 같은데 이게 필요한건가???
//    @GetMapping("/user-info")
//    @ResponseBody
//    public UserInfoDto getUserInfo (@AuthenricaionPrincipal UserDetailsImpl userDetails) {
//        String username = userDetails.getUser().getUsername();
//
//        return new UserInfoDto()
//    }
}
