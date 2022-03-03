package com.jym.langCM02.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter // 15-2 게시판 디테일 구현
public class Board { // 13-1 게시판 생성 구현

    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String detail;

    private LocalDateTime regDate = LocalDateTime.now();
    private LocalDateTime updateDate = LocalDateTime.now();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<Article> articles = new ArrayList<>();

    public static Board createBoard(String name, String detail) {

        Board board = new Board();

        board.name = name;
        board.detail = detail;

        return board;

    }

}
