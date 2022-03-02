package com.jym.langCM02.service;

import com.jym.langCM02.config.Role;
import com.jym.langCM02.dao.MemberRepository;
import com.jym.langCM02.domain.Member;
import com.jym.langCM02.dto.MemberSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService { // 9 MemberController와 연결하기 위해 MemberService 작성함

    private final MemberRepository memberRepository; // 12 MemberRepository 인터페이스 만든 후, 이곳에서 사용하기

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // 11 오버라이드된 메소드 자동 입력함
        return memberRepository.findByLoginId(username).get(); // 12 MemberRepository 인터페이스 만든 후, 이곳에서 사용하기
    }
    // 3-3 회원 중복 로직 작성
    /**
     * 회원 가입 중복 확인
     * @param loginId
     * @param nickname
     * @param email
     */
    public void isDuplicateMember(String loginId, String nickname, String email){
        if(memberRepository.existByLoginId(loginId)){
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        } else if(memberRepository.existByNickname(nickname)){
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        } else if(memberRepository.existByEmail(email)){
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }



    // 3-2 회원가입 로직 작성 시작
    /**
     * 회원 가입
     * @param memberSaveForm
     */
    public void save(MemberSaveForm memberSaveForm) throws IllegalStateException{

        // 3-3 회원가입 로직 수정
        isDuplicateMember(
                memberSaveForm.getLoginId(),
                memberSaveForm.getNickname(),
                memberSaveForm.getEmail()
        );

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Member member = Member.createMember(
                memberSaveForm.getLoginId(),
                bCryptPasswordEncoder.encode(memberSaveForm.getLoginPw()),
                memberSaveForm.getName(),
                memberSaveForm.getNickname(),
                memberSaveForm.getEmail(),
                Role.MEMBER
        );

        memberRepository.save(member);

    }

}
