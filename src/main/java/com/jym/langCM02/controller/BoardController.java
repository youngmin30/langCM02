package com.jym.langCM02.controller;

import com.jym.langCM02.dto.board.BoardSaveForm;
import com.jym.langCM02.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class BoardController { // 13-6

    private final BoardService boardService;

    public String showAddBoard(Model model){

        model.addAttribute("boardSaveForm", new BoardSaveForm());

        return "usr/board/add";
    }

    public String doAddBoard(BoardSaveForm boardSaveForm) {

        boardService.save(boardSaveForm);

        return "redirect:/";
    }


}
