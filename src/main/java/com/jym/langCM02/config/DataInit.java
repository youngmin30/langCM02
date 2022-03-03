package com.jym.langCM02.config;

import com.jym.langCM02.dao.MemberRepository;
import com.jym.langCM02.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DataInit { // 16

    private final InitService initService;

    public void init() {
        initService.initAdmin();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final MemberRepository memberRepository;

        public void initAdmin() {

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

            Member admin = Member.createMember(
                    "admin",
                    bCryptPasswordEncoder.encode("admin"),
                    "관리자",
                    "관리자",
                    "admin@admin.com",
                    Role.ADMIN
            );

            memberRepository.save(admin);
        }
    }
}
