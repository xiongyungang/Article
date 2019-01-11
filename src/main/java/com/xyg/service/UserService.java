package com.xyg.service;

import com.xyg.domain.User;

/**
 * 用户操作Service
 */
public interface UserService {
    /**
     * 登陆判断
     * @param phoneNo 手机号
     * @param password 密码
     * @return
     */
    User getUserByPhoneNoAndPwd(String phoneNo, String password);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    User upload(User user);

    /**
     * 根据token获取用户
     * @param token
     * @return
     */
    User getUserByToken(String token);
}
