package com.xyg.service.impl;

import com.xyg.domain.User;
import com.xyg.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Test
    public void getUserByToken() throws Exception {
        Assert.assertNotNull(userService.getUserByToken("a2c42022a726453565c6f5f59fcae1f3"));
    }

    @Test
    public void upload() throws Exception {
        User user = userService.getUserByPhoneNoAndPwd("12345678910","123456");
        user.setLoginToken("123");
        userService.upload(user);
    }

    @Autowired
    UserService userService;
    @Test
    public void isUserByPhoneNoAndPwd() throws Exception {
        System.out.println(userService.getUserByPhoneNoAndPwd("12345678910","123456"));
    }

}