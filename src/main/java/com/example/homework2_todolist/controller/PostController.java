package com.example.homework2_todolist.controller;

import com.example.homework2_todolist.dto.PostAddRequestDto;
import com.example.homework2_todolist.dto.PostResponseDto;
import com.example.homework2_todolist.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/posts")
@RequiredArgsConstructor
public class PostController {
// ================================================== 2
    private final PostService postService;
// ==================================================== 4
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponseDto addPost(@RequestBody PostAddRequestDto requestDto) {
// =================================================================== 3(public void) ,  5(public PostResponseDto)
        PostResponseDto responseDto = postService.addPost(requestDto);
        return responseDto;
//===================================================================== 6
    }
}
