package com.jym.langCM02.service;

import com.jym.langCM02.dao.ArticleRepository;
import com.jym.langCM02.domain.Article;
import com.jym.langCM02.domain.Member;
import com.jym.langCM02.dto.article.ArticleSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService { // 8-3-4

    private final ArticleRepository articleRepository;

    @Transactional
    public void save(ArticleSaveForm articleSaveForm, Member member) {

        Article article = Article.createArticle(
                articleSaveForm.getTitle(),
                articleSaveForm.getBody()
        );

        article.setMember(member);
        articleRepository.save(article);

    }

}
