package com.jym.langCM02.dao;

import com.jym.langCM02.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> { // 8-3-3



}
