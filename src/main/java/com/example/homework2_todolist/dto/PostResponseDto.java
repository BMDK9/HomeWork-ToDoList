package com.example.homework2_todolist.dto;

import com.example.homework2_todolist.entity.PostEntity;
import lombok.Getter;

import java.time.LocalDateTime;

public record PostResponseDto (
//    ======================================================= 5 , 11 public class -> public record
     Long id,
     String title,
     String author,
     String content,
     LocalDateTime createdAt
) {
       public PostResponseDto(PostEntity savePost) {
        this(savePost.getId(), savePost.getTitle(), savePost.getAuthor(),
            savePost.getContents(),savePost.getCreatedAt());
    }
//    ======================================= 10, 11 this( ); 로 바꿈.
}
