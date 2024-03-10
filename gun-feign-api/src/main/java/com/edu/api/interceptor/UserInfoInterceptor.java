package com.edu.api.interceptor;

import com.edu.common.constants.SystemConstants;
import com.edu.common.utils.UserContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.util.StringUtils;

public class UserInfoInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        String userId= UserContext.getUserId();
        if(StringUtils.hasText(userId)){
        template.header(SystemConstants.USER_INFO, userId);
    }}
}
