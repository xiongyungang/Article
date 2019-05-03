package com.xyg.repository;

import com.xyg.domain.Picture;
import com.xyg.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture,Integer>{
    /**
     * 根据用户查询所有图片
     * @param user
     * @return
     */
    List<Picture> findPictureByUser(User user);
}
