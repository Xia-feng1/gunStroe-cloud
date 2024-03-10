package com.edu;

import com.edu.api.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients(basePackages = "com.edu.api.client",defaultConfiguration = DefaultFeignConfig.class)
@MapperScan("com.edu.store.mapper")
@SpringBootApplication
public class StoreApp {
    public static void main(String[] args) {
        SpringApplication.run(StoreApp.class,args);
    }
}