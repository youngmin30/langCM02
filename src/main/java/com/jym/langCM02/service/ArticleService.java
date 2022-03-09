package com.jym.langCM02.service;

import com.jym.langCM02.dao.ArticleRepository;
import com.jym.langCM02.domain.Article;
import com.jym.langCM02.domain.Board;
import com.jym.langCM02.domain.Member;
import com.jym.langCM02.dto.article.ArticleDTO;
import com.jym.langCM02.dto.article.ArticleModifyForm;
import com.jym.langCM02.dto.article.ArticleSaveForm;
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
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public void save(ArticleSaveForm articleSaveForm, Member member, Board board) {

       Article article = Article.createArticle(
                articleSaveForm.getTitle(),
                articleSaveForm.getBody()
        );

        article.setMember(member);
        article.setBoard(board);
        articleRepository.save(article);
    }
    public ArticleDTO getArticle(Long id) throws NoSuchElementException {
        Article findArticle = getById(id);
        ArticleDTO articleDTO = new ArticleDTO(findArticle);
        return articleDTO;
    }
    public Optional<Article> findById(Long id){
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
    public void modifyArticle(ArticleModifyForm articleModifyForm, Board board,  Long id){
        Article findArticle = getById(id);

        findArticle.modifyArticle(
                articleModifyForm.getTitle(),
                articleModifyForm.getBody()
        );

        findArticle.setBoard(board);
    }

    public List<ArticleDTO> getList() {
        List<Article> articleList = articleRepository.findAll();
        List<ArticleDTO> articleDTOList = new ArrayList<>();
        for (Article article : articleList) {
            ArticleDTO articleDTO = new ArticleDTO(article);
            articleDTOList.add(articleDTO);
        }
        return articleDTOList;
    }
    public void delete(Long id){
        Article findArticle = getById(id);
        articleRepository.delete(findArticle);
    }
}