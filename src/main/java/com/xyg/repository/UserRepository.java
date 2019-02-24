package com.xyg.repository;

import com.xyg.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByPhoneAndPassword(String phoneNo,String password);

    User findUserByLoginToken(String token);

    /**
     * 通过电话查询出用户
     *
     */
    List<User> findUserByPhone(String phone)  ;

    /**
     * 通过昵称判断该用户是否存在
     *
     */
    List<User> findUserByUserName(String userName)  ;
}
