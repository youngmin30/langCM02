package com.jym.langCM02.dto.article;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ArticleSaveForm { // 8-3-1

    @NotBlank
    private String title;
    @NotBlank
    private String body;

    private Long board_id; // 34 게시글 작성 로직 개선

}
