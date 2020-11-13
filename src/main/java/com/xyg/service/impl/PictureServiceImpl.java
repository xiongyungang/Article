package com.xyg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.xyg.domain.Picture;
import com.xyg.domain.User;
import com.xyg.repository.PictureRepository;
import com.xyg.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureRepository pictureRepository;

    // 设置好账号的ACCESS_KEY和SECRET_KEY
    String ACCESS_KEY = "23-uSJ2D2J_RP6w1LKUSKbJxP-nobo9-y921WUxc";
    String SECRET_KEY = "V4whDc_ztc1to5G7R5q1pg7K7DF0roBwQOuBARl7";
    // 要上传的空间
    String bucketname = "xygspace";

    // 密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    // 构造一个带指定Zone对象的配置类,不同的七云牛存储区域调用不同的zone
    Configuration cfg = new Configuration(Zone.zone2());
    // ...其他参数参考类注释
    UploadManager uploadManager = new UploadManager(cfg);

    // 域名
    private static String QINIU_IMAGE_DOMAIN = "http://qiniu.xiongyungang.top/";

    // 简单上传，使用默认策略，只需要设置上传的空间名就可以了
    private String getUpToken() {
        return auth.uploadToken(bucketname);
    }

    // 七牛云文件上传
    public String saveImage(MultipartFile file, String fileExt) {
        try {
            String filename = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;
            // 调用put方法上传
            Response res = uploadManager.put(file.getBytes(), filename, getUpToken());
            // 返回打印信息
            if (res.isOK() && res.isJson()) {
                return QINIU_IMAGE_DOMAIN + JSONObject.parseObject(res.bodyString()).get("key");
            } else {
                return null;
            }
        } catch (QiniuException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * 根据用户查找所有图片
     *
     * @param user
     * @return
     */
    @Override
    public List<Picture> getPictureByUser(User user) {
        return pictureRepository.findPictureByUser(user);
    }

    /**
     * 保存图片
     *
     * @param picture
     */
    @Override
    public void save(Picture picture) {
        pictureRepository.save(picture);
    }

    /**
     * 删除图片
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        pictureRepository.delete(id);
    }


}
