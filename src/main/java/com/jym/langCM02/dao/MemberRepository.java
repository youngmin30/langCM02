package com.jym.langCM02.dao;

import com.jym.langCM02.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

        Optional<Member> findByLoginId(String loginId);
        
        // 3-3 아래 작성
        boolean existByLoginId(String loginId);
        boolean existByNickname(String nickname);
        boolean existByEmail(String email);

}
