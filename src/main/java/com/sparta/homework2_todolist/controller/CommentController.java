//package com.sparta.homework2_todolist.controller;
//
//import com.sparta.homework2_todolist.dto.CommentRequestDto;
//import com.sparta.homework2_todolist.dto.CommentResponseDto;
//import com.sparta.homework2_todolist.security.UserDetailsImpl;
//import com.sparta.homework2_todolist.service.CommentService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/comments")
//@RequiredArgsConstructor
//public class CommentController {
//    private final CommentService commentService;
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public CommentResponseDto addComment(@RequestBody CommentRequestDto commentRequestDto,
//                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return commentService.addComment(commentRequestDto, userDetails.getUser());
//    }
//}
