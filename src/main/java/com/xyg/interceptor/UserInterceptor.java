package com.xyg.interceptor;

import com.xyg.domain.User;
import com.xyg.service.UserService;
import com.xyg.utils.CookieUtil;
import com.xyg.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 实现自动登陆
 */
public class UserInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //获取用户
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (sessionUser != null) {
            //已经登陆，放行
            return true;
        } else {
            //在Cookie中获取token
            String loginToken = CookieUtil.findCookieByName(request, "loginToken");
            if (StringUtils.isNotBlank(loginToken)) {
                //根据token找用户
                User userByToken = userService.getUserByToken(loginToken);
                if (userByToken != null) {
                    //有对应token的用户，保存到session，放行
                    request.getSession().setAttribute("user", userByToken);
                    return true;
                } else {
                    //没有对应用户，清除Cookie
                    CookieUtil.clearCookie(request, response, "loginToken");
                    return false;
                }
            } else {

                //没有cookie，也没有登陆。是index请求获取用户信息，可以放行
                if (request.getRequestURI().contains("session")) {
                    return true;
                }

                //没有cookie凭证，跳转到登陆页
                response.sendRedirect("/login.html");
                return false;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
