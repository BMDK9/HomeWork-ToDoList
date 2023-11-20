package com.sparta.homework2_todolist.controller.exception;

public class CardNoAuthorityException extends RuntimeException{
    public CardNoAuthorityException (String msg) {
        super(msg);
    }
}
