package com.sparta.homework2_todolist.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class CommonResponse {

    private final String message;
    private final Integer code;

//    public static CommonResponse ok() {
//        return CommonResponse.builder().message(CommonCode.OK.getMessage())
//            .code(CommonCode.OK.getCode()).build();
//    }
//    public static CommonResponse idno() {
//        return CommonResponse.builder().message(CommonCode.IDNO.getMessage())
//            .code(CommonCode.IDNO.getCode()).build();
//    }
//    public static CommonResponse pwno() {
//        return CommonResponse.builder().message(CommonCode.PWNO.getMessage())
//            .code(CommonCode.PWNO.getCode()).build();
//    }
}
