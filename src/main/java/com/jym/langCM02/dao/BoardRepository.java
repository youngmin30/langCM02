package com.jym.langCM02.dao;

import com.jym.langCM02.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> { // 13-6
    Optional<Board> findByName(String name); // 17-3 게시판 수정 구현

}
