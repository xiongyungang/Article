package com.xyg.service.impl;

import com.xyg.domain.Picture;
import com.xyg.domain.User;
import com.xyg.repository.PictureRepository;
import com.xyg.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureRepository pictureRepository;

    /**
     * 根据用户查找所有图片
     * @param user
     * @return
     */
    @Override
    public List<Picture> getPictureByUser(User user) {
        return pictureRepository.findPictureByUser(user);
    }

    /**
     * 保存图片
     * @param picture
     */
    @Override
    public void save(Picture picture) {
        pictureRepository.save(picture);
    }

    /**
     * 删除图片
     * @param id
     */
    @Override
    public void delete(Integer id) {
        pictureRepository.delete(id);
    }


}
