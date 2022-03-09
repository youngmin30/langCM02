package com.jym.langCM02.service;

import com.jym.langCM02.dao.ArticleRepository;
import com.jym.langCM02.domain.Article;
import com.jym.langCM02.domain.Member;
import com.jym.langCM02.dto.article.ArticleModifyForm;
import com.jym.langCM02.dto.article.ArticleSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

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

    // 19-3 게시물 수정 구현
    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    public Article getById(Long id){

        Optional<Article> articleOptional = findById(id);

        articleOptional.orElseThrow(
                () -> new
                        NoSuchElementException("해당 게시물은 존재하지 않습니다.")
        );

        return articleOptional.get();

    }

    @Transactional
    public void modifyArticle(ArticleModifyForm articleModifyForm, Long id){
        Article findArticle = getById(id);

        findArticle.modifyArticle(
                articleModifyForm.getTitle(),
                articleModifyForm.getBody()
        );
    }

}
