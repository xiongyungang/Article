package com.xyg.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user")
public class User implements Serializable{
    private Integer userId;

    @NotNull(message = "昵称不能为空")
    @Size(min = 2, max = 18, message = "昵称最低2位、最高18位")
    private String userName;

    @NotNull(message = "手机号码不能为空")
    @Size(min = 11, max = 11, message = "手机号码只能是11位")
    private String phone;

    @NotNull(message = "密码不能为空")
    @Size(min = 6, max = 18, message = "密码只能是6到18位")
    private String password;

    private Date registerTime = new Date();

    private Integer validity = 0;

    private String email;

    //头像
    private String headPortrait = "";

    //登陆凭证
    private String loginToken = "";

    public User() {
    }

    @Id
    @GeneratedValue
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", registerTime=" + registerTime +
                ", validity=" + validity +
                ", email='" + email + '\'' +
                ", headPortrait='" + headPortrait + '\'' +
                ", loginToken='" + loginToken + '\'' +
                '}';
    }
}
