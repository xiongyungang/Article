package com.xyg.repository;

import com.xyg.domain.Favorite;
import com.xyg.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite,Integer>,JpaSpecificationExecutor<Favorite> {
    /**
     * 根据用户获取所有收藏
     * @param user
     * @return
     */
    List<Favorite> findFavoriteByUser(User user);
}
