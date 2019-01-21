package com.xyg.controller;

import com.xyg.domain.Article;
import com.xyg.domain.Category;
import com.xyg.domain.User;
import com.xyg.service.ArticleService;
import com.xyg.service.CategoryService;
import com.xyg.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 文章Controller
 */
@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 首页下拉Ajax传递的数据
     *
     * @return
     */
    @RequestMapping(value = "/findOne")
    @ResponseBody
    public Article findOne() {
        return articleService.findArticleByAjax();
    }

    /**
     * 文章列表
     *
     * @return
     */
    @GetMapping(value = {"/articles/{tag}/{start}","/articles"})
    public String articles(@PathVariable(value = "tag",required = false) String tag,@PathVariable(value = "start",required = false) Integer start, Model model) {
        tag = tag==null?"所有文章":tag;
        start = start==null?0:start;
        Page<Article> articles = articleService.pageCategorySort(tag, start);
        List<Category> categorys = categoryService.getAll();
        model.addAttribute("articles", articles);
        model.addAttribute("categorys", categorys);
        model.addAttribute("current",tag);
        return "/article";
    }

    /**
     * 保存文章
     *
     * @param article
     * @return
     */
    @PostMapping(value = "/article", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result saveArticle(Article article,String cateName,HttpSession session) {
        Category category = categoryService.getByName(cateName);
        //补全信息
        article.setCreateTime(new Date());
        article.setCategory(category);
        //补全用户信息
        User user = (User) session.getAttribute("user");
        article.setUser(user);
        article.setAuthor(user.getUserName());
        articleService.saveArticle(article);
        return Result.success("ok");
    }

    /**
     * 更新文章
     *
     * @param article
     * @return
     */
    @PutMapping(value = "/article", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result updateArticle(Article article) {
        System.out.printf(article.toString());
        Article articleById = articleService.findArticleById(article.getArticleId());
        //创建时间
        article.setCreateTime(articleById.getCreateTime());
        article.setAuthor(articleById.getAuthor());
        article.setUser(articleById.getUser());
        article.setCategory(articleById.getCategory());

        articleService.saveArticle(article);
        return Result.success("ok");
    }

    /**
     * 删除文章
     * @param articleId
     * @return
     */
    @DeleteMapping(value = "/article", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result deleteArticleById(Integer articleId) {
        articleService.deleteArticleById(articleId);
        return Result.success("ok");
    }
}
