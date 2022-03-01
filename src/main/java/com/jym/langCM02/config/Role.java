package com.jym.langCM02.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role { // 3 enum으로 Role 만들기
    
    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER");
    
    private String value;
    
}
