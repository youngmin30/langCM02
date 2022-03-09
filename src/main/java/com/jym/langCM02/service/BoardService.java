package com.jym.langCM02.service;

import com.jym.langCM02.dao.BoardRepository;
import com.jym.langCM02.domain.Article;
import com.jym.langCM02.domain.Board;
import com.jym.langCM02.dto.article.ArticleListDTO;
import com.jym.langCM02.dto.board.BoardDTO;
import com.jym.langCM02.dto.board.BoardModifyForm;
import com.jym.langCM02.dto.board.BoardSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    // // 15-5 게시판 디테일 구현
    public Optional<Board> findById(Long id){
        return boardRepository.findById(id);
    }

    public BoardDTO getBoardDetail(Long id){
        Optional<Board> boardOptional = findById(id);

        boardOptional.orElseThrow(
                () -> new NoSuchElementException("해당 게시판은 존재하지 않습니다.")
        );

        Board findBoard = boardOptional.get();

        List<ArticleListDTO> articleList = new ArrayList<>();
        List<Article> articles = findBoard.getArticles();

        for(Article article : articles) {
            ArticleListDTO articleListDTO = new ArticleListDTO(article);
            articleList.add(articleListDTO);
        }

        return new BoardDTO(findBoard, articleList);

    }

    // // 17-4 게시판 수정 구현
    @Transactional
    public Long modify(BoardModifyForm boardModifyForm) throws NoSuchElementException{

        Optional<Board> boardOptional = boardRepository.findByName(boardModifyForm.getName());

        boardOptional.orElseThrow(
                () -> new NoSuchElementException("해당 게시판은 존재하지 않습니다.")
        );

        Board board = boardOptional.get();

        board.modifyBoard(
                boardModifyForm.getName(),
                boardModifyForm.getDetail()
        );

        return board.getId();
    }
}
