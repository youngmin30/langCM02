package com.jym.langCM02.domain;

import com.jym.langCM02.config.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member implements UserDetails {

    @Id
    @Column(name = "member_id") // 13-2 추가
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 2 DB의 PK에 연결할 id

    private String loginId; // 1 오버라이드된 메소드들의 필드
    private String loginPw; // 1 오버라이드된 메소드들의 필드

    private String name;
    private String nickname;
    private String email;


    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)  // 8-3-6
    private List<Article> articles = new ArrayList<>(); // 8-3-6

    private LocalDateTime regDate = LocalDateTime.now(); // 11-1 Member에 등록일과 수정일 추가
    private LocalDateTime updateDate = LocalDateTime.now(); // 11-1 Member에 등록일과 수정일 추가

    @Enumerated(EnumType.STRING) // 4 Role.enum을 만든 후 이곳에서 사용함
    private Role authority;

    private boolean isAccountNonExpired = true; // 1 오버라이드된 메소드들의 필드
    private boolean isAccountNonLocked = true; // 1 오버라이드된 메소드들의 필드
    private boolean isCredentialsNonExpired = true; // 1 오버라이드된 메소드들의 필드
    private boolean isEnabled = true; // 1 오버라이드된 메소드들의 필드

    // 13 생성 메소드 추가
    public static Member createMember(String loginId, String loginPw, String name, String nickname, String email, Role authority ) {

        Member member = new Member();

        member.loginId = loginId;
        member.loginPw = loginPw;

        member.name = name;
        member.nickname = nickname;
        member.email = email;

        member.authority = authority;

        return member;

    }

    // 12-4 modifyMember 작성
    public void modifyMember(String loginPw, String nickname, String email) {
        this.loginPw = loginPw;
        this.nickname = nickname;
        this.email = email;
    }


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
        return isAccountNonExpired; // 4-2 리턴값을 필드 이름으로 변경
    }

    @Override // 7 직접 작성함
    public boolean isAccountNonLocked(){
        return isAccountNonLocked; // 4-2 리턴값을 필드 이름으로 변경
    }

    @Override // 7 직접 작성함
    public boolean isCredentialsNonExpired(){
        return isCredentialsNonExpired; // 4-2 리턴값을 필드 이름으로 변경
    }

    @Override // 7 직접 작성함
    public boolean isEnabled(){
        return isEnabled; // 4-2 리턴값을 필드 이름으로 변경
    }

}
