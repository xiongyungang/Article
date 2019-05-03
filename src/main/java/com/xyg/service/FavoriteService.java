package com.xyg.service;

import com.xyg.domain.Article;
import com.xyg.domain.Favorite;
import com.xyg.domain.User;
import com.xyg.utils.Result;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FavoriteService {
    public List<Favorite> getFavoriteByUser(User user, Integer start);
    public Result addFavorite(User user,Integer articleId);
    public Result deleteFavoriteById(Favorite favorite);
    public Result deleteFavoriteByProperty(User user,Integer articleId);
    public Result isFavorite(User user,Integer articleId);
}
