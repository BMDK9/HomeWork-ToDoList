package com.sparta.homework2_todolist.entity;

import com.sparta.homework2_todolist.dto.CardRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "card")
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
    private boolean done;

    @Column(nullable = false)
    private boolean hidden;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "card")
    private List<Comment> commentEntities = new ArrayList<>();

    public Card(CardRequestDto cardRequestDto, User user) {
        this.title = cardRequestDto.getTitle();
        this.contents = cardRequestDto.getContent();
        this.user = user;
        this.done = false;
        this.hidden = false;

//        ============================================ 8
    }

    public void update(CardRequestDto cardRequestDto) {
        this.title = cardRequestDto.getTitle();
        this.contents = cardRequestDto.getContent();
    }

    public void changeStatus () {
        done = !done;
    }

    public void hideCard () {
        hidden = !hidden;
    }
}
