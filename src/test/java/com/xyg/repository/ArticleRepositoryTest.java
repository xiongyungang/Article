package com.xyg.repository;

import com.xyg.domain.Article;
import com.xyg.domain.Category;
import com.xyg.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ArticleRepositoryTest {
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void findAll() {
        List<Article> articles = articleRepository.findAll();
        System.out.println(articles.toString());
    }

    @Test
    public void findByTime() throws ParseException {
        User user = new User();
        user.setUserId(1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
        Date start = sdf.parse("2018-7-1");
        Date end = sdf.parse("2018-7-31");
        List<Article> list = articleRepository.findArticleByUserAndCreateTimeBetweenOrderByCreateTimeDesc(user, start, end);
        for (Article article : list
                ) {
            System.out.println(article.toString());
        }
    }

    @Test
    public void findById() throws Exception {
        Article one = articleRepository.findOne(1);
        System.out.println(one.getComments());
    }

    @Test
    public void findAllByCateAndPage() {


        /*Page<Article> page = articleRepository.findAll(specification, pageable);
        System.out.println("当前页面集合" + page.getContent().size());
        System.out.println("总页数" + page.getTotalPages());
        System.out.println("总记录数" + page.getTotalElements());
        System.out.println("当前页数" + page.getNumber() +1);
        System.out.println("当前页面的记录数" + page.getNumberOfElements());*/
    }
}
