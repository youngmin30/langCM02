package com.jym.langCM02.dao;

import com.jym.langCM02.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> { // 13-6


}
