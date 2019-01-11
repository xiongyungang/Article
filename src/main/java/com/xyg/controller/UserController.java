package com.xyg.controller;

import com.xyg.domain.User;
import com.xyg.service.UserService;
import com.xyg.utils.CookieUtil;
import com.xyg.utils.Result;
import com.xyg.utils.WebUtils;
import com.xyg.utils.vcode.Captcha;
import com.xyg.utils.vcode.GifCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * 用户Controller
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 获取gif验证码（初始化和点击获取）
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/user/gifCode")
    public void getGifCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置响应格式
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/gif");

        // 生成gif格式动画验证码 宽，高，位数。
        Captcha captcha = new GifCaptcha(146, 42, 4);

        ServletOutputStream out = response.getOutputStream();
        captcha.out(out);
        request.getSession().setAttribute("captcha", captcha.text().toLowerCase());
    }

    /**
     * 用户登陆
     * @param mobileNo
     * @param password
     * @param inputCaptcha
     * @param session
     * @param response
     * @return
     */
    @PostMapping(value = "/user/session", produces = {"application/json;charset=UTF-8"})
    public Result login(String mobileNo, String password, String inputCaptcha, HttpSession session, HttpServletResponse response) {
        //判断验证码
        if (WebUtils.validateCaptcha(inputCaptcha, "captcha", session)) {
            //判断用户及密码
            User user = userService.getUserByPhoneNoAndPwd(mobileNo, password);
            if (user!=null) {
                //设置自动登陆，将token保存在数据库中
                String loginToken = WebUtils.md5(new Date().toString() + session.getId());
                user.setLoginToken(loginToken);
                User uploadUser = userService.upload(user);

                //保存session和cookie
                session.setAttribute("user",uploadUser);
                CookieUtil.addCookie(response, "loginToken", loginToken, 604800);
                return Result.success(uploadUser);
            } else {
                return Result.error("账号密码错误");
            }
        } else {
            return Result.error("验证码错误");
        }
    }

    /**
     *  用户退出
     * @param session
     * @param request
     * @param response
     * @return
     */
    @DeleteMapping(value = "/session",produces = {"application/json;charset=UTF-8"})
    public Result logout(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
        //清除session和Cookie
        session.removeAttribute("user");
        CookieUtil.clearCookie(request, response, "loginToken");
        return Result.success();
    }
}
