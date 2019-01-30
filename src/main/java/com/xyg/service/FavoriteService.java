package com.xyg.service;

import com.xyg.domain.Favorite;
import com.xyg.domain.User;
import org.springframework.data.domain.Page;

public interface FavoriteService {
    public Page<Favorite> getFavoriteByUser(User user, Integer start);
    public boolean isFavorite(User user,Integer articleId);
}
