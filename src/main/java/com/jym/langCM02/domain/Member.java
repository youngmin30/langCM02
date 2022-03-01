package com.jym.langCM02.domain;

import com.jym.langCM02.config.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 2 DB의 PK에 연결할 id

    private String loginId; // 1 오버라이드된 메소드들의 필드
    private String loginPw; // 1 오버라이드된 메소드들의 필드

    private String name;
    private String nickname;
    private String email;

    @Enumerated(EnumType.STRING) // 4 Role.enum을 만든 후 이곳에서 사용함
    private Role authority;

    private boolean isAccountNonExpired = true; // 1 오버라이드된 메소드들의 필드
    private boolean isAccountNonLocked = true; // 1 오버라이드된 메소드들의 필드
    private boolean isCredentialsNonExpired = true; // 1 오버라이드된 메소드들의 필드
    private boolean isEnabled = true; // 1 오버라이드된 메소드들의 필드


    // 5 해당 메소드들을 아래와 같이 바꾸기
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(this.authority.getValue()));

        return authorities;
    }

    // 6 오버라이드 메소드(ctrl + i)
    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override // 7 직접 작성함
    public boolean isAccountNonExpired(){
        return false;
    }

    @Override // 7 직접 작성함
    public boolean isAccountNonLocked(){
        return false;
    }

    @Override // 7 직접 작성함
    public boolean isCredentialsNonExpired(){
        return false;
    }

    @Override // 7 직접 작성함
    public boolean isEnabled(){
        return false;
    }

}
