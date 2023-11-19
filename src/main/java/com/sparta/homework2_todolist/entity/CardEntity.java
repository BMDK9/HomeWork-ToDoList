package com.sparta.homework2_todolist.entity;

import com.sparta.homework2_todolist.dto.CardRequestDto;
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
    private Long cardId;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 20)
    private String author;
//    @Column(nullable = false)
//    private String password;
    @Column(nullable = false, length = 500)
    private String contents;

// ================================================= 7
    public CardEntity(CardRequestDto cardRequestDto) {
        this.title = cardRequestDto.getTitle();
        this.author = cardRequestDto.getAuthor();
        this.contents = cardRequestDto.getContent();

//        ============================================ 8
    }

    public void update(CardRequestDto cardRequestDto) {
        this.title = cardRequestDto.getTitle();
        this.author = cardRequestDto.getAuthor();
        this.contents = cardRequestDto.getContent();
    }
}
