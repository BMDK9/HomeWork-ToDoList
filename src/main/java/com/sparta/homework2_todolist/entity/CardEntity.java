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
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 500)
    private String contents;
// ================================================= 7

    @Column(nullable = false)
    private boolean done;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    public CardEntity(CardRequestDto cardRequestDto, UserEntity userEntity) {
        this.title = cardRequestDto.getTitle();
        this.contents = cardRequestDto.getContent();
        this.userEntity = userEntity;
        this.done = false;

//        ============================================ 8
    }

    public void update(CardRequestDto cardRequestDto) {
        this.title = cardRequestDto.getTitle();
        this.contents = cardRequestDto.getContent();
    }

    public void changeStatus () {
        done = !done;
    }
}
