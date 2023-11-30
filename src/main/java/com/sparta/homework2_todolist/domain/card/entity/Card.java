package com.sparta.homework2_todolist.domain.card.entity;

import com.sparta.homework2_todolist.domain.card.dto.CardRequestDto;
import com.sparta.homework2_todolist.domain.users.entity.User;
import com.sparta.homework2_todolist.domain.utills.TimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Card extends TimeEntity {

    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 500)
    private String contents;
// ================================================= 7

    @Column(nullable = false)
    private boolean isDone;

    @Column(nullable = false)
    private boolean isHidden;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    private Card(String title, String contents, User user, Boolean isDone, Boolean isHidden) {
        this.title = title;
        this.contents = contents;
        this.user = user;
        this.isDone = isDone;
        this.isHidden = isHidden;
//        ============================================ 8
    }

    public void update(CardRequestDto cardRequestDto) {
        this.title = cardRequestDto.getTitle();
        this.contents = cardRequestDto.getContent();
    }

    public Boolean changeStatus() {
        isDone = !isDone;
        return this.isDone;
    }

    public Boolean hideCard() {
        isHidden = !isHidden;
        return this.isHidden;
    }
}
