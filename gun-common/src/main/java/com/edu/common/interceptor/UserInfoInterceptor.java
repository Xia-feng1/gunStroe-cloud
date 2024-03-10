package com.edu.common.interceptor;

import cn.hutool.core.util.StrUtil;
import com.edu.common.constants.SystemConstants;
import com.edu.common.utils.UserContext;
import io.swagger.models.auth.In;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInfoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头中用户
        String userInfo=request.getHeader(SystemConstants.TOKEN_KEY);
        //判断是否为空，不为空存入UserContext
        if(StrUtil.isNotBlank(userInfo)){
            UserContext.setUserId(userInfo);
        }
        //放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清除用户
        UserContext.removeUser();
    }
}
