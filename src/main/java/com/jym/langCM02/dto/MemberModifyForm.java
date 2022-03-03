package com.jym.langCM02.dto;

import lombok.Data;

@Data
public class MemberModifyForm { // 12-3 MemberModifyForm 작성

    private String loginPw; // 12-4 Member.java와 연결되는 부분
    private String nickname;
    private String email;

}
