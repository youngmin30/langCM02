package com.jym.langCM02.dto;

import lombok.Data;

@Data
public class MemberSaveForm {

    // 3(커밋번호) - 1 MemberSaveForm에 회원가입에 필요한 정보 작성
    private String loginId;
    private String loginPw;
    private String name;
    private String nickname;
    private String email;

}
