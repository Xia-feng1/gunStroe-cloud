package com.edu.getway.filter;

import cn.hutool.core.text.AntPathMatcher;
import com.edu.common.constants.SystemConstants;
import com.edu.getway.config.AuthProperties;
import com.edu.common.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(AuthProperties.class)
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    private final AuthProperties authProperties;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求
        ServerHttpRequest request = exchange.getRequest();
        //判断路径
        if(isExclude(request.getPath().toString())){
           return chain.filter(exchange);
        }
        //不是获取token
        String token = null;
        List<String> headers = request.getHeaders().get(SystemConstants.TOKEN_KEY);
        if(!headers.isEmpty()&&headers!=null){
            token=headers.get(0);
        }
        // 有token 则解析token
        String userId=null;
        try {
            userId = JwtUtils.parseJwt(token).getSubject();

        } catch (Exception e) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        //传递用户信息
        String userInfo=userId;
        ServerWebExchange build = exchange.mutate().request(builder -> builder.header(SystemConstants.TOKEN_KEY, userInfo)).build();
        //放行
        return chain.filter(build);
    }
    //是否为放行路径
    private boolean isExclude(String antPath) {
        for (String pathPattern : authProperties.getExcludePaths()) {
            if(antPathMatcher.match(pathPattern, antPath)){
                return true;
            }
        }
        return false;
    }
    @Override
    public int getOrder() {
        return 0;
    }
}
