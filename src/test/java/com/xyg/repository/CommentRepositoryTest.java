package com.xyg.repository;

import com.xyg.domain.Article;
import com.xyg.domain.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void findCommentsByArticle() throws Exception {
        Article article = new Article();
        article.setArticleId(2);
        List<Comment> comments = commentRepository.findCommentsByArticle(article);
    }

}