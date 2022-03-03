package com.jym.langCM02.dto.article;

import com.jym.langCM02.domain.Article;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleListDTO {

    private Long id;

    private String title;
    private String nickname;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    public ArticleListDTO(Article article){ // 15-3 게시판 디테일 구현
        this.id = article.getId();
        this.title = article.getTitle();
        this.nickname = article.getMember().getNickname();
        this.regDate = article.getRegDate();
        this.updateDate = article.getUpdateDate();
    }

}
