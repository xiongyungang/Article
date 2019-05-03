package com.xyg.controller;

import com.xyg.domain.*;
import com.xyg.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
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
    @Autowired
    private CommentService commentService;
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private PictureService pictureService;

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
        return "editArticle";
    }

    /**
     * 跳转文章详细页面
     * @param articleId 文章id
     * @param model
     * @return
     */
    @GetMapping(value = "/article/details/{articleId}")
    public String details(@PathVariable("articleId") Integer articleId, Model model, HttpSession session) {
        Article article = articleService.findArticleById(articleId);
        List<Comment> comments = commentService.getComments(article);
        User user =(User) session.getAttribute("user");
        if (user == null) {
            //游客不可编辑文章
            model.addAttribute("status", "true");
            model.addAttribute("article", article);
            model.addAttribute("comments", comments);
        }else {
            //判断当前用户可否编辑
            //todo:有bug，有时已经登陆不显示
            if (user.getUserId().equals(article.getUser().getUserId())) {
                model.addAttribute("status", "false");
            } else {
                model.addAttribute("status", "true");
            }
            model.addAttribute("article", article);
            model.addAttribute("comments", comments);
        }
        return "detailArticle";
    }

    /**
     * 跳转编辑文章页面
     */
    @GetMapping(value = "/edit/{articleId}")
    public String editArticle(@PathVariable("articleId") Integer articleId, Model model,HttpSession session) {
        //获取文章
        Article article = articleService.findArticleById(articleId);
        //todo:传递错误信息
        //判断文章和用户的关系
        User user = (User) session.getAttribute("user");
        if (!article.getUser().getUserId().equals(user.getUserId())) {
            return "/error";
        }
        //获取分类
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categorys", categories);
        model.addAttribute("article", article);
        return "editArticle";
    }

    /**
     * 个人中心
     * @return
     */
    @GetMapping(value = "/personal")
    public String personal(@RequestParam(name="v" ,defaultValue = "1") String count,Model model,HttpSession session) {
        User user = (User) session.getAttribute("user");
        switch (count)
        {
            case "1":
                model.addAttribute("user", user);
                return "personal"+count;
            case "2":
                //todo:分页、优化
                List<Favorite> favorites = favoriteService.getFavoriteByUser(user, 5);
                ArrayList<Article> articleList = new ArrayList();
                for (Favorite favorite :
                        favorites) {
                    Article article = favorite.getArticle();
                    Article articleById = articleService.findArticleById(article.getArticleId());
                    articleList.add(articleById);
                }
                model.addAttribute("articles", articleList);
                return "personal"+count;
            case "3":
                //todo:分页
                List<Article> articles = articleService.findArticleByUser(user);
                model.addAttribute("articles", articles);
                return "personal"+count;
            case "4":
                List<Picture> pictures = pictureService.getPictureByUser(user);
                Collections.reverse(pictures);
                model.addAttribute("pictures",pictures);
                return "personal"+count;
            default:
                model.addAttribute("user", user);
                return "personal"+count;
        }
    }

}
