package com.epoch.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SaTokenConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，定义详细认证规则
        registry.addInterceptor(new SaInterceptor(handler -> {
            // 指定一条 match 规则
            SaRouter
                    .match("/comment/**")
                    .match("/label-directory/**")
                    .match("/notification/**")
                    .match("/notification/**")
                    .match("/post/**")
                    .match("/topic/**")
                    .match("/user/**")
                    .notMatch("/user/login")
                    .notMatch("/user/register")
                    .notMatch("/user/retrievePwd")
                    .check(r -> StpUtil.checkPermission("user.*"));

            SaRouter
                    .match("/admin/**")
                    .check(r -> StpUtil.checkPermission("admin.*"));

        })).addPathPatterns("/**");
    }
}
