package com.jym.langCM02.service;

import com.jym.langCM02.dao.BoardRepository;
import com.jym.langCM02.domain.Board;
import com.jym.langCM02.dto.board.BoardSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void save(BoardSaveForm boardSaveForm){

        Board board = Board.createBoard(
                boardSaveForm.getName(),
                boardSaveForm.getDetail()
        );

        boardRepository.save(board);

    }

    // 14-2
    public List<Board> findAll(){
        return boardRepository.findAll();
    }

}
