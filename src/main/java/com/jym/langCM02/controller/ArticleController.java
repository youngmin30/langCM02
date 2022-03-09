package com.jym.langCM02.controller;

import com.jym.langCM02.domain.Article;
import com.jym.langCM02.domain.Member;
import com.jym.langCM02.dto.article.ArticleDTO;
import com.jym.langCM02.dto.article.ArticleModifyForm;
import com.jym.langCM02.dto.article.ArticleSaveForm;
import com.jym.langCM02.service.ArticleService;
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
public class ArticleController { // 8-3-5

    private final ArticleService articleService;
    private final MemberService memberService;

    @GetMapping("/articles/write")
    public String showArticleWrite(Model model) {

        model.addAttribute("articleSaveForm", new ArticleSaveForm());

        return "usr/article/write";

    }

    @PostMapping("/articles/write")
    public String doWrite(@Validated ArticleSaveForm articleSaveForm, BindingResult bindingResult, Model model, Principal principal) {

        if(bindingResult.hasErrors()){
            return "usr/article/write"; // write.html 만들어야 함
        }

        try {
            Member findMember = memberService.findByLoginId(principal.getName());

            articleService.save(
                    articleSaveForm,
                    findMember
            );

        } catch (IllegalStateException e){
            model.addAttribute("err_msg", e.getMessage());
            return "usr/article/write";

        }
        return "redirect:/";


    }

    // 19-4 게시물 수정 구현
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

    // 20-3 게시물 리스트 구현
    @GetMapping("/articles/")
    public String showList(Model model){

        List<ArticleDTO> articleList = articleService.getList();
        model.addAttribute("articleList", articleList);

        return "usr/article/list";
    }

    // 21-1 게시물 삭제 구현
    @GetMapping("/articles/delete/{id}")
    public String deleteArticle(@PathVariable(name = "id") Long id, Principal principal) {
        try {
            ArticleDTO article = articleService.getArticle(id);

            if(article.getAuthorName() != principal.getName()) {
                return "redirect:/";
            }
            articleService.delete(id);
            return "redirect:/";

        } catch (Exception e) {
            return "redirect:/";
        }
    }

    public String showDetail(@PathVariable(name="id") Long id, Model model) {
        try {
            ArticleDTO findArticle = articleService.getArticle(id);
            model.addAttribute("article", findArticle);

            return "usr/article/detail";
        } catch (Exception e) {
            return "redirect:/";
        }
    }

}
