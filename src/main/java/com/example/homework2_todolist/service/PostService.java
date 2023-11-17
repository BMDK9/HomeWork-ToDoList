package com.example.homework2_todolist.service;

import com.example.homework2_todolist.dto.PostAddRequestDto;
import com.example.homework2_todolist.dto.PostResponseDto;
import com.example.homework2_todolist.entity.ToDoEntity;
import com.example.homework2_todolist.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostJpaRepository postJpaRepository;
    //================================================ 9

    public PostResponseDto addPost(PostAddRequestDto requestDto) {
// ==================================================== 4
        //Dto -> entity
        ToDoEntity toDoEntity = new ToDoEntity(requestDto);
//         =============================================== 8
        ToDoEntity savePost = postJpaRepository.save(toDoEntity);
//        ===================================== 9
        return new PostResponseDto(savePost);
//        ===================================== 10
    }

    public PostResponseDto getPost(Long postId) {
        ToDoEntity toDoEntity = postJpaRepository.findById(postId)
            .orElseThrow(() -> new NullPointerException("해당 포스트는 없습니다."));

        return new PostResponseDto(toDoEntity);
    }

    public List<PostResponseDto> getPosts() {
        return postJpaRepository.findAllByOrderByCreatedAtDesc().stream()
            .map(PostResponseDto::new)
            .collect(Collectors.toList());
    }
}
