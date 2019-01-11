package com.xyg.controller;

import com.xyg.domain.Article;
import com.xyg.domain.Category;
import com.xyg.service.ArticleService;
import com.xyg.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 页面跳转Controller
 */
@Controller
public class PageController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 跳转发布文章页面
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/postArticle")
    public String postArticle(Model model) {
        //获取分类
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categorys", categories);
        return "/editArticle";
    }

    /**
     * 跳转文章详细页面
     * @param articleId 文章id
     * @param model
     * @return
     */
    @GetMapping(value = "/article/details/{articleId}")
    public String details(@PathVariable("articleId") Integer articleId, Model model) {
        Article article = articleService.findArticleById(articleId);
        model.addAttribute("article", article);
        return "/detailArticle";
    }

    /**
     * 跳转编辑文章页面
     */
    @GetMapping(value = "/edit/{articleId}")
    public String editArticle(@PathVariable("articleId") Integer articleId, Model model) {
        //获取文章
        Article article = articleService.findArticleById(articleId);
        //获取分类
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categorys", categories);
        model.addAttribute("article", article);
        return "/editArticle";
    }

    @GetMapping(value = "/personal")
    public String personal() {
        return "/personal";
    }

}
