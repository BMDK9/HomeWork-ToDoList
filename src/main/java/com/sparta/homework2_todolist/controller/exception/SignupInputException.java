package com.sparta.homework2_todolist.controller.exception;

public class SignupInputException extends RuntimeException {

    public SignupInputException (String msg) {
        super(msg);
    }
}
