package com.xyg.controller;

import com.xyg.domain.User;
import com.xyg.service.UserService;
import com.xyg.utils.CookieUtil;
import com.xyg.utils.Result;
import com.xyg.utils.WebUtils;
import com.xyg.utils.note.SendSmsDemo;
import com.xyg.utils.vcode.Captcha;
import com.xyg.utils.vcode.GifCaptcha;
import org.omg.CORBA.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * 用户Controller
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *
     * @param user
     * @param captcha
     * @param bindingResult
     * @param session
     * @return
     */
    @PostMapping(value = "/user", produces = {"application/json;charset=UTF-8"})
    public Result register(@Valid User user, String captcha, BindingResult bindingResult, HttpSession session) {
        System.out.println(user.toString());
        try {
            // 传过来的参数错误
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            if (allErrors != null && allErrors.size() > 0) {
                logger.error(bindingResult.getFieldError().getDefaultMessage());
                return Result.error("提交的参数错误");
            }

            //判断验证码是否正确
            if (WebUtils.validateCaptcha(captcha, "validateCode", session)) {

                //判断完就把验证码删除了
                session.removeAttribute("validateCode");

                // 注册
                User user1 = userService.userRegister(user);
                if (user1 != null) {
                    return Result.success(null);
                } else {
                    return Result.error("未知错误");
                }
            } else {
                return Result.error("验证码错误");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("注册失败");
        }
    }

    /**
     * 发送手机验证码(设置验证码到session中)
     */
    @GetMapping("/user/mobileCode")
    public void getMobileCode(HttpSession session, String mobileNo) {
        //生成4位随机数
        try {
            String fourRandom = WebUtils.getFourRandom();
            SendSmsDemo.sendSMS(mobileNo, "3", new String[]{fourRandom, "3"}, session);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("手机验证码出错");
        }
    }

    /**
     * 检查手机是否已被使用（由Juqery-validation的remote调用，返回true或者false）
     */
    @GetMapping("/user/mobileNo")
    public void validateMobileNo(String mobileNo, PrintWriter writer) {
        List<User> users = userService.findUserByMobileNo(mobileNo);
        if (users != null && users.size() > 0) {
            writer.write("false");
        } else {
            writer.write("true");
        }

    }

    /**
     * 检查昵称是否已被使用（由Juqery-validation的remote调用，返回true或者false）
     */
    @GetMapping("/user/userNickName")
    public void validateUserNickName(String userNickName, PrintWriter writer) {
        List<User> users = userService.findUserByUserNickName(userNickName);
        if (users != null && users.size() > 0) {
            writer.write("false");
        } else {
            writer.write("true");
        }
    }

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
