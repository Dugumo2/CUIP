package com.epoch.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.epoch.mapper") // 指定 Mapper 接口的扫描路径
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 可选配置：设置方言类型，不同的数据库需要设置不同的方言类型
        paginationInterceptor.setDialectType("mysql");
        return paginationInterceptor;
    }
}
