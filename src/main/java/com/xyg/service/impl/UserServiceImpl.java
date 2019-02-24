package com.xyg.service.impl;

import com.xyg.domain.User;
import com.xyg.repository.UserRepository;
import com.xyg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public User getUserByPhoneNoAndPwd(String phoneNo, String password) {
        return userRepository.findUserByPhoneAndPassword(phoneNo,password);
    }

    @Override
    public User upload(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByToken(String token) {
        return userRepository.findUserByLoginToken(token);
    }

    @Override
    public User userRegister(User user) {
        user.setValidity(1);
        return userRepository.save(user);
    }

    @Override
    public List<User> findUserByMobileNo(String mobileNo) {
        return userRepository.findUserByPhone(mobileNo);
    }

    @Override
    public List<User> findUserByUserNickName(String userNickName) {
        return userRepository.findUserByUserName(userNickName);
    }

}
