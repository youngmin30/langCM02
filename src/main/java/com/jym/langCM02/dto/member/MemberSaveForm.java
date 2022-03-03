// package com.jym.langCM02.dto;
package com.jym.langCM02.dto.member; // 8-3-7

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberSaveForm {

    // 3(커밋번호) - 1 MemberSaveForm에 회원가입에 필요한 정보 작성
    @NotBlank // 4-6 MemberSaveForm의 각 필드에 @NotBlank 추가
    private String loginId;
    @NotBlank
    private String loginPw;
    @NotBlank
    private String name;
    @NotBlank
    private String nickname;
    @NotBlank
    private String email;

}
