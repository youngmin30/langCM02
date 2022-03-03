package com.jym.langCM02.controller;

import com.jym.langCM02.domain.Member;
import com.jym.langCM02.dto.member.MemberLoginForm;
import com.jym.langCM02.dto.member.MemberModifyForm;
import com.jym.langCM02.dto.member.MemberSaveForm;
import com.jym.langCM02.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

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
                return "usr/member/join"; // 이전 단계에서 signup을 join으로 바꾼 것
            }
            return "redirect:/";
        }

        // 7-3 회원가입 로직 아래, login.html로 이동하는 로직 작성
        public String showLogin(Model model){

            model.addAttribute("memberLoginForm", new MemberLoginForm());
            return "usr/member/login";
        }

        // 12-6 member modify 로직 작성
        @GetMapping("/members/modify")
        public String showModify(Model model, Principal principal){
            Member findMember = memberService.findByLoginId(principal.getName());

            model.addAttribute("member", findMember);
            model.addAttribute("membermodifyForm", new MemberModifyForm());

            return "usr/member/modify";
        }

        public String doModify(MemberModifyForm memberModifyForm, Principal principal, Model model){
            try{
                memberService.modifyMember(memberModifyForm, principal.getName());
            } catch (Exception e){
                model.addAttribute("err_msg", e.getMessage());
                return "usr/member/modify";
            }
            return "redirect:/";
        }

}
