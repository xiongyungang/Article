package com.xyg.service.impl;

import com.xyg.domain.Favorite;
import com.xyg.domain.User;
import com.xyg.service.FavoriteService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FavoriteServiceImplTest {
    @Autowired
    private FavoriteService favoriteService;

    @Test
    public void getFavoriteByUser() throws Exception {
        User user = new User();
        user.setUserId(1);
        Page<Favorite> page = favoriteService.getFavoriteByUser(user, 0);
        List<Favorite> favorites = page.getContent();

        for (Favorite f :
                favorites) {
            System.out.println(f.getCreateTime());
        }
    }

    @Test
    public void isFavorite() throws Exception {
        User user = new User();
        user.setUserId(2);
        System.out.println(favoriteService.isFavorite(user, 1));
    }

}