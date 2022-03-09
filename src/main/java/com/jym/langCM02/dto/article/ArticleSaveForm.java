package com.jym.langCM02.dto.article;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ArticleSaveForm { // 8-3-1

    @NotBlank
    private String title;
    @NotBlank
    private String body;

    private Long board_id;

}
