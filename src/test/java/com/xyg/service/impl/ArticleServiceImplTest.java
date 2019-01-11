package com.xyg.service.impl;

import com.xyg.domain.Article;
import com.xyg.domain.User;
import com.xyg.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ArticleServiceImplTest {
    @Autowired
    private ArticleService articleService;

    @Test
    public void pageCategorySort() throws Exception {
        Page<Article> page = articleService.pageCategorySort("新闻", 1);
        System.out.println("当前页面集合" + page.getContent().size());
        System.out.println("总页数" + page.getTotalPages());
        System.out.println("总记录数" + page.getTotalElements());
        System.out.println("当前页数" + page.getNumber());
        System.out.println("当前页面的记录数" + page.getNumberOfElements());
    }

    @Test
    public void testFindArticleByAjax() {
        Article article = new Article();
        article = articleService.findArticleByAjax();
        System.out.println(article);
    }

    @Test
    public void testFindAll(){
        List<Article> all = articleService.findArticleAll();
        for (Article article : all) {
            System.out.println(article.toString());
        }
    }

    @Test
    public void testFindByCategory(){
        List<Article> list = articleService.findArticleByCategory("java");
        System.out.println(list.size());
        for (Article article : list) {
            System.err.println(article.toString());
        }
    }

    @Test
    public void testFindByUser(){
        User user = new User();
        user.setUserId(1);
        List<Article> list = articleService.findArticleByUser(user);
        for (Article article : list) {
            System.err.println(article.toString());
        }
    }

    @Test
    public void saveArticle() {
        Article article = new Article();

        article.setCreateTime(new Date());
        article.setTitle("测试文章标题");
        article.setContent("测试文章内容");

        articleService.saveArticle(article);
    }

    @Test
    public void update() {
        Article article = new Article();
        article.setArticleId(1);
        article.setTitle("hello");
        articleService.updateArticle(article);
    }

}