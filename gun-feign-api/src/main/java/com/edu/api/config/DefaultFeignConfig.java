package com.edu.api.config;

import com.edu.common.constants.SystemConstants;
import com.edu.common.utils.UserContext;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;

public class DefaultFeignConfig {
    //日志打印
//    @Bean
//    public Logger.Level feignLogLevel(){
//        return Logger.Level.FULL;
//    }
    @Bean
    public RequestInterceptor userInfoInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                //获取用户信息其实就是id
                String userId = UserContext.getUserId();
                if(userId == null) {
                    // 如果为空则直接跳过
                    return;
                }
                // 如果不为空则放入请求头中，传递给下游微服务
                template.header(SystemConstants.USER_INFO,userId);
            }
        };
    }
}