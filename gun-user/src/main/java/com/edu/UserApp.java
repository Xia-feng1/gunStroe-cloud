package com.edu;

import com.edu.api.config.DefaultFeignConfig;
import com.edu.user.service.impl.GunReturnListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
@EnableFeignClients(basePackages = "com.edu.api.client",defaultConfiguration = DefaultFeignConfig.class)
@MapperScan("com.edu.user.mapper")
@SpringBootApplication
public class UserApp {
    public static void main(String[] args) {

        SpringApplication.run(UserApp.class,args);
    }
}