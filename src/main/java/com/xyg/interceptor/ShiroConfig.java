package com.xyg.interceptor;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

		/*
		 * 添加shiro内置过滤器:
		 * 		anon:无需认证
		 * 		authc:必须认证才可以访问
		 * 		user:如果使用remember的功能才可以访问
		 * 		perms:该资源必须得到资源权限才可以访问
		 * 		roles:该资源必须得到角色权限才可以访问
		 */
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        filterChainDefinitionMap.put("/", "anon");
		filterChainDefinitionMap.put("/article/**", "anon");
		filterChainDefinitionMap.put("/articles/**","anon");
		filterChainDefinitionMap.put("/css/**","anon");
		filterChainDefinitionMap.put("/js/**","anon");
		filterChainDefinitionMap.put("/img/**","anon");
		filterChainDefinitionMap.put("/register.html","anon");
		filterChainDefinitionMap.put("/login.html","anon");
		filterChainDefinitionMap.put("/user/**","anon");
		filterChainDefinitionMap.put("/error/**","anon");
		filterChainDefinitionMap.put("/findOne","anon");

        filterChainDefinitionMap.put("/**","authc");
//        //添加授权访问
//        filterChainDefinitionMap.put("/add","perms[user:add]");
//        filterChainDefinitionMap.put("/update","perms[user:update]");
//        filterChainDefinitionMap.put("/*", "authc");
//
        //修改跳转页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");
//        //自定义授权页面
//        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");
//
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     * @param userRealm
     * @return
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建realm
     * @return
     */
    @Bean(name="userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }
}
