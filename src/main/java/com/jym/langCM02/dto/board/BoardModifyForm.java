package com.jym.langCM02.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardModifyForm { // 17-2 게시판 수정 구현

    private Long id; // 27-1 게시판 수정 페이지 구현

    private String name;

    private String detail;

}
