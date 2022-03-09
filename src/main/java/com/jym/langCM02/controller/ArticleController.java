package com.jym.langCM02.controller;

import com.jym.langCM02.domain.Article;
import com.jym.langCM02.domain.Board;
import com.jym.langCM02.domain.Member;
import com.jym.langCM02.dto.article.ArticleDTO;
import com.jym.langCM02.dto.article.ArticleModifyForm;
import com.jym.langCM02.dto.article.ArticleSaveForm;
import com.jym.langCM02.dto.board.BoardDTO;
import com.jym.langCM02.service.ArticleService;
import com.jym.langCM02.service.BoardService;
import com.jym.langCM02.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final MemberService memberService;
    private final BoardService boardService;

    @GetMapping("/boards/{id}/articles/write")
    public String showArticleWrite(@PathVariable(name = "id")Long id, Model model) {
        BoardDTO boardDetail = boardService.getBoardDetail(id);
        model.addAttribute("board", boardDetail);
        model.addAttribute("articleSaveForm", new ArticleSaveForm());
        return "usr/article/write";
    }
    @PostMapping("/boards/{id}/articles/write")
    public String doWrite(@Validated ArticleSaveForm articleSaveForm, BindingResult bindingResult, Model model, Principal principal, @PathVariable(name = "id")Long id) {
        if (bindingResult.hasErrors()) {
            return "usr/article/write";
        }
        try {
            Board findBoard = boardService.getBoard(articleSaveForm.getBoard_id());
            Member findMember = memberService.findByLoginId(principal.getName());
            articleService.save(
                    articleSaveForm,
                    findMember,
                    findBoard
            );
        } catch (IllegalStateException e) {
            model.addAttribute("err_msg", e.getMessage());
            return "usr/article/write";
        }
        return "redirect:/articles";
    }
    @GetMapping("/articles/modify/{id}")
    public String showModify(@PathVariable(name = "id") Long id, Model model){
        try {
            Article article = articleService.getById(id);
            model.addAttribute("articleModifyForm", new ArticleModifyForm(
                    article.getTitle(),
                    article.getBody()
            ));
            return "usr/article/modify";
        }catch (Exception e){
            return "redirect:/";
        }
    }
    @PostMapping("/articles/modify/{id}")
    public String doModify(@PathVariable(name = "id") Long id, ArticleModifyForm articleModifyForm){
        try{
            articleService.modifyArticle(articleModifyForm, id);
            return "redirect:/article/"+ id;
        }catch (Exception e){
            return "usr/article/modify";
        }
    }
    @GetMapping("/articles")
    public String showList(Model model) {

        List<ArticleDTO> articleList = articleService.getList();

        ArticleDTO articleDTO = articleList.get(0);

        model.addAttribute("boardName", articleDTO.getBoardName());
        model.addAttribute("articleList", articleList);

        return "usr/article/list";
    }
    @GetMapping("/articles/delete/{id}")
    public String deleteArticle(@PathVariable(name = "id") Long id, Principal principal){
        try {
            ArticleDTO article = articleService.getArticle(id);
            if(article.getAuthorName() != principal.getName()){
                return "redirect:/";
            }
            articleService.delete(id);
            return "redirect:/";
        }catch (Exception e){
            return "redirect:/";
        }
    }
    @GetMapping("/articles/{id}")
    public String showDetail(@PathVariable(name = "id") Long id, Model model){
        try {
            ArticleDTO findArticle = articleService.getArticle(id);
            model.addAttribute("article", findArticle);
            return "usr/article/detail";
        }catch (Exception e){
            return "redirect:/";
        }
    }
}