package com.jym.langCM02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 6-1 컨트롤러 클래스에 어노테이션 추가
public class HomeController {

    // 6-2 index.html로 이동하는 로직
    @GetMapping("/")
    public String showIndex(){
        return "index";
    }
}
