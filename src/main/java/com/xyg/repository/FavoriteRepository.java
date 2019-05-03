package com.xyg.repository;

import com.xyg.domain.Article;
import com.xyg.domain.Favorite;
import com.xyg.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite,Integer>,JpaSpecificationExecutor<Favorite> {
    /**
     * 根据用户获取所有收藏
     * @param user
     * @return
     */
    List<Favorite> findFavoriteByUser(User user);

    /**
     * 根据用户、文章删除收藏
     * @param article
     * @param user
     */
    //todo:service事务
    @Transactional
    void deleteByArticleAndUser(Article article,User user);

    List<Favorite> findFavoriteByUserOrderByCreateTimeDesc(User user);
}
