package com.jym.langCM02.controller;

import com.jym.langCM02.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MemberController { // 8 Member Entity 만든 후 MemberController 작성함

    private final MemberService memberService; // 10 MemberService 작성 후 이곳에서 가져오기

}
