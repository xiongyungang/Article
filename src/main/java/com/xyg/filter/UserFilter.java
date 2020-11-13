package com.xyg.filter;

import com.xyg.domain.User;
import com.xyg.service.UserService;
import com.xyg.utils.CookieUtil;
import com.xyg.utils.SpringContextUtil;
import com.xyg.utils.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
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
                UserService userService = (UserService)SpringContextUtil.getBean(UserService.class);
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
}
