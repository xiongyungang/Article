package com.xyg.service;

import com.xyg.domain.Article;
import com.xyg.domain.Favorite;
import com.xyg.domain.User;
import com.xyg.utils.Result;
import org.springframework.data.domain.Page;

public interface FavoriteService {
    public Page<Favorite> getFavoriteByUser(User user, Integer start);
    public Result addFavorite(User user,Integer articleId);
    public Result deleteFavoriteById(Favorite favorite);
    public Result deleteFavoriteByProperty(Article article,User user);
    public Result isFavorite(User user,Integer articleId);
}
