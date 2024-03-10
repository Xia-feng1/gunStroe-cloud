package com.edu;

import com.edu.api.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.edu.api.client",defaultConfiguration = DefaultFeignConfig.class)
@MapperScan("com.edu.lend.mapper")
@SpringBootApplication
public class LendApp {
    public static void main(String[] args) {
        SpringApplication.run(LendApp.class,args);
    }
}