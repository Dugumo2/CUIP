package com.epoch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.epoch.mapper")
public class CuipuApplication {

    public static void main(String[] args) {
        SpringApplication.run(CuipuApplication.class, args);
    }

}
