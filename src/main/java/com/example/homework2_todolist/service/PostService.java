package com.example.homework2_todolist.service;

import com.example.homework2_todolist.dto.PostAddRequestDto;
import com.example.homework2_todolist.dto.PostResponseDto;
import com.example.homework2_todolist.entity.PostEntity;
import com.example.homework2_todolist.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostJpaRepository postJpaRepository;
    //================================================ 9

    public PostResponseDto addPost(PostAddRequestDto requestDto) {
// ==================================================== 4
        //Dto -> entity
        PostEntity postEntity = new PostEntity(requestDto);
//         =============================================== 8
        PostEntity savePost = postJpaRepository.save(postEntity);
//        ===================================== 9
        return new PostResponseDto(savePost);
//        ===================================== 10
    }

    public PostResponseDto getPost(Long postId) {
        PostEntity postEntity = postJpaRepository.findById(postId)
            .orElseThrow(() -> new NullPointerException("해당 포스트는 없습니다."));

        return new PostResponseDto(postEntity);
    }
}
