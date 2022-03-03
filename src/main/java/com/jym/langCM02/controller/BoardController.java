package com.jym.langCM02.controller;

import com.jym.langCM02.domain.Board;
import com.jym.langCM02.dto.board.BoardSaveForm;
import com.jym.langCM02.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController { // 13-6

    private final BoardService boardService;

    @GetMapping("/boards/add")
    public String showAddBoard(Model model){

        model.addAttribute("boardSaveForm", new BoardSaveForm());

        return "usr/board/add";
    }

    @PostMapping("boards/add")
    public String doAddBoard(BoardSaveForm boardSaveForm) {

        boardService.save(boardSaveForm);

        return "redirect:/";
    }

    // 14-1 게시판 리스트
    @GetMapping("/boards")
    public String showBoardList(Model model) {
        List<Board> boardList = boardService.findAll();
        model.addAttribute("boardList", boardList);
        return "usr/board/list";
    }

}
