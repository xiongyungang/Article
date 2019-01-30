package com.xyg.service.impl;

import com.xyg.domain.Favorite;
import com.xyg.domain.User;
import com.xyg.repository.FavoriteRepository;
import com.xyg.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;

    //每页显示数
    private Integer PAGE_SIZE = 5;

    /**
     * 根据用户获取收藏，分页,按收藏时间逆序
     * @param user
     * @param start 分页起始下标
     * @return
     */
    @Override
    public Page<Favorite> getFavoriteByUser(User user,Integer start) {
        start = start<0?0:start;
        Integer size = PAGE_SIZE;

        //排序和分页
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "createTime");
        Sort sort = new Sort(order);
        Pageable pageable = new PageRequest(start, size, sort);

        return favoriteRepository.findAll(pageable);
    }

    /**
     * 判断是否收藏
     * @param articleId
     * @return
     */
    @Override
    public boolean isFavorite(User user,Integer articleId) {
        List<Favorite> favorites = favoriteRepository.findFavoriteByUser(user);

        //todo:改为jpa查询
        for (Favorite f :
                favorites) {
            if (f.getArticle().getArticleId().equals(articleId)) {
                return true;
            }
        }
        return false;
    }
}
