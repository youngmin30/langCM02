package com.jym.langCM02.controller;

import com.jym.langCM02.domain.Board;
import com.jym.langCM02.dto.board.BoardDTO;
import com.jym.langCM02.dto.board.BoardModifyForm;
import com.jym.langCM02.dto.board.BoardSaveForm;
import com.jym.langCM02.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/adm") // 17-5 게시판 수정 구현
@RequiredArgsConstructor
public class BoardController { // 13-6

    private final BoardService boardService;

    @GetMapping("/boards/add")
    public String showAddBoard(Model model){

        model.addAttribute("boardSaveForm", new BoardSaveForm());

        return "adm/board/add"; // 17-6 게시판 수정 구현(usr를 adm으로 바꾸고, adm에 member, article, board, general 디렉토리 만들기)
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
        return "adm/board/list"; // 17-7 게시판 수정 구현
    }

    // 15-4 게시판 디테일 구현
    public String showBoardDetail(@PathVariable(name="id") Long id, Model model) {

        try {
            BoardDTO boardDetail = boardService.getBoardDetail(id);
            model.addAttribute("board", boardDetail);
        } catch (Exception e) {
            return "redirect:/";
        }
        return "adm/board/detail"; // 17-8 게시판 수정 구현
    }

    // 17-9 게시판 수정 구현
    @GetMapping("boards/modify")
    public String showModifyBoard(Model model) {
        model.addAttribute("boardModifyForm", new BoardModifyForm());
        return "adm/board/modify";
    }

    @PostMapping("/boards/modify")
    public String doModifyBoard(BoardModifyForm boardModifyForm) {
        try {
            boardService.modify(boardModifyForm);
        } catch (Exception e) {
            return "adm/board/modify";
        }

        return "redirect:/";
    }

    // 18-2 게시판 삭제 구현
    @GetMapping("/boards/delete/{id}")
    public String doDeleteBoard(@PathVariable(name="id") Long id) {
        try {
            boardService.delete(id);
            return "adm/board/list";
        } catch (Exception e) {
            return "adm/board/list";
        }
    }
}
