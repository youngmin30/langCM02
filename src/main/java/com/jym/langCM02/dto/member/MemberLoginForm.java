// package com.jym.langCM02.dto;
package com.jym.langCM02.dto.member;

import lombok.Data;

@Data
public class MemberLoginForm { // 7-1 MemberLoginForm 작성

    // 7-2 로그인 화면에서 받는 값 작성
    private String loginId;

    private String loginPw;

}
