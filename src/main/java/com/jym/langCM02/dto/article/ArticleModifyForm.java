package com.jym.langCM02.dto.article;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleModifyForm {
    private String title;
    private String body;

    private Long board_id;

}