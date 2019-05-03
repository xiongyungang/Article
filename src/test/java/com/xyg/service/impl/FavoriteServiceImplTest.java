package com.xyg.service.impl;

import com.xyg.domain.Favorite;
import com.xyg.domain.User;
import com.xyg.service.FavoriteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FavoriteServiceImplTest {
    @Autowired
    private FavoriteService favoriteService;

    @Test
    public void test(){
        User user = new User();
        user.setUserId(1);
//        Page<Favorite> favorites = favoriteService.getFavoriteByUser(user, 5);
//        System.out.println("favorites = " + favorites.getContent().size());
    }
}