package com.sparta.homework2_todolist.entity;

import com.sparta.homework2_todolist.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String CommentContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    public Comment(Long id, CommentRequestDto commentRequestDto,
                   User user, Card card) {
        this.id = id;
        this.CommentContent = commentRequestDto.getCommentContent();
        this.user = user;
        this.card = card;
    }
}
