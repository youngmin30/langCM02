package com.jym.langCM02.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter // 15-1 게시판 디테일 구현
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article { // 8-3-1 Article 객체 만들기

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String body;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @ManyToOne(fetch = FetchType.LAZY) // 13-3
    @JoinColumn(name = "boader_id") // 13-3
    private Board board; // 13-3

    private LocalDateTime regDate = LocalDateTime.now();
    private LocalDateTime updateDate = LocalDateTime.now();


    // 생성 메소드
    public static Article createArticle(String title, String body){

        Article article = new Article();

        article.title = title;
        article.body = body;

        return article;
    }

    // 연관관계 메소드
    public void setMember(Member member) {
        this.member = member;
        member.getArticles().add(this); // ========
    }

}
