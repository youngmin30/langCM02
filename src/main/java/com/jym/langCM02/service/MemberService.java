package com.jym.langCM02.service;

import com.jym.langCM02.dao.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService { // 9 MemberController와 연결하기 위해 MemberService 작성함

    private final MemberRepository memberRepository; // 12 MemberRepository 인터페이스 만든 후, 이곳에서 사용하기

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // 11 오버라이드된 메소드 자동 입력함
        return memberRepository.findByLoginId(username).get(); // 12 MemberRepository 인터페이스 만든 후, 이곳에서 사용하기
    }
}
