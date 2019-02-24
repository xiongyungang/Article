package com.xyg.service;

import com.xyg.domain.User;

import java.util.List;

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

    /**
     * 用户注册
     * @param user
     * @return
     */
    User userRegister(User user);

    /**
     * 通过电话查询出用户
     *
     * @param mobileNo
     * @return
     */
    List<User> findUserByMobileNo(String mobileNo)  ;

    /**
     * 通过昵称判断该用户是否存在
     *
     * @param userNickName
     * @return
     */
    List<User> findUserByUserNickName(String userNickName)  ;
}
