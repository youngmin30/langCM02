package com.jym.langCM02.dto.article;

import lombok.AllArgsConstructor;
import lombok.Data;

// 19-1 게시물 수정 구현
@Data
@AllArgsConstructor
public class ArticleModifyForm {

    private String title;
    private String body;

}