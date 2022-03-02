package com.jym.langCM02.controller;

import com.jym.langCM02.dto.MemberSaveForm;
import com.jym.langCM02.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController { // 8 Member Entity 만든 후 MemberController 작성함

    private final MemberService memberService; // 10 MemberService 작성 후 이곳에서 가져오기

        // 4-2 회원가입 버튼을 누르면 회원가입 페이지로 이동하는 로직 작성
        @GetMapping("/members/join")
        public String showSignup(Model model) {
            model.addAttribute("memberSaveForm", new MemberSaveForm());
            return "usr/member/join";
        }

        // 4-5 회원가입 페이지에서 확인을 누르면 회원가입이 이루어지는 로직 작성
        @PostMapping("members/join")
        public String doSignup(@Validated MemberSaveForm memberSaveForm, BindingResult bindingResult, Model model) {

            if(bindingResult.hasErrors()){
                return "usr/member/join";
            }
            try {
                memberService.save(memberSaveForm);
            } catch (Exception e) {
                model.addAttribute("err_msg", e.getMessage());
                return "usr/member/signup";
            }
            return "redirect:/";
        }

}
