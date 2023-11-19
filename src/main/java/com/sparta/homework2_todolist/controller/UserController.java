package com.sparta.homework2_todolist.controller;

import com.sparta.homework2_todolist.dto.SignupRequestDto;
import com.sparta.homework2_todolist.response.CommonCode;
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
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {

        userService.signup(signupRequestDto);

        return ResponseEntity.ok(CommonCode.OK.getMessage());
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
