package com.jym.langCM02.dto.article;

import com.jym.langCM02.domain.Article;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleDTO {
    private Long id;
    private String title;
    private String body;

    private String authorName;

    private Long boardId;
    private String boardName;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;


    public ArticleDTO(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.body = article.getBody();
        this.authorName = article.getMember().getNickname();

        this.boardId = article.getBoard().getId();
        this.boardName = article.getBoard().getName();

        this.regDate = article.getRegDate();
        this.updateDate = article.getUpdateDate();
    }
}