package com.xyg.service;

import com.xyg.domain.Picture;
import com.xyg.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PictureService {
    List<Picture> getPictureByUser(User user);
    void save(Picture picture);
    void delete(Integer id);
    String saveImage(MultipartFile file, String fileExt);
}
