package com.jym.langCM02.dto.board;

import com.jym.langCM02.domain.Board;
import com.jym.langCM02.dto.article.ArticleListDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BoardDTO { // 15-4 게시판 디테일 구현

    private Long id;

    private String name;
    private String detail;

    private List<ArticleListDTO> articleListDTO;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    public BoardDTO(Board board, List<ArticleListDTO> articleListDTO) {

        this.id = board.getId();
        this.name = board.getName();
        this.detail = board.getDetail();
        this.articleListDTO = articleListDTO;
        this.regDate = board.getRegDate();
        this.updateDate = board.getUpdateDate();
    }
}
