package com.xyg.repository;

import com.xyg.domain.Article;
import com.xyg.domain.Favorite;
import com.xyg.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FavoriteRepositoryTest {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Test
    public void save() {
        Favorite favorite = new Favorite();
        User user = new User();
        user.setUserId(1);
        favorite.setUser(user);

        Article article = new Article();
        article.setArticleId(1);
        favorite.setArticle(article);

        favoriteRepository.save(favorite);
    }

    @Test
    public void findByUser(){
        User user = new User();
        user.setUserId(1);
        List<Favorite> favorites = favoriteRepository.findFavoriteByUser(user);
        for (Favorite favorite: favorites) {
            System.out.println(favorite.getCreateTime());
        }
    }

    @Test
    public void delete() {
        Favorite favorite = new Favorite();
        User user = new User();
        user.setUserId(1);
        favorite.setUser(user);

        Article article = new Article();
        article.setArticleId(1);
        favorite.setArticle(article);

        favorite.setId(1);

        favoriteRepository.deleteByArticleAndUser(article,user);
    }
}