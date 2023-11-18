package com.example.homework2_todolist.entity;

import com.example.homework2_todolist.dto.RequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "card")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 20)
    private String author;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, length = 500)
    private String contents;

// ================================================= 7
    public CardEntity(RequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContent();

//        ============================================ 8
    }

    public void update(RequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.contents = requestDto.getContent();
    }
}
