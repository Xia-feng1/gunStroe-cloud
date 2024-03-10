package com.edu.common.aop;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edu.common.utils.UserContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // 定义切点
    @Pointcut("@annotation(com.edu.common.annotations.LogExecution)")
    public void logExecutionAnnotation() {}

    // 环绕通知
    @Around("logExecutionAnnotation()")
    public Object  logWebRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取HTTP请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取请求信息
        String httpMethod = request.getMethod();
        String uri = request.getRequestURI();
        String remoteAddr = request.getRemoteAddr();
        String userId = UserContext.getUserId();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        long startTime = System.currentTimeMillis();
        logger.info("请求: {} {} {}.{}() with arguments[s] = {} 用户编号{} 用户ip {}", httpMethod, uri, className, methodName, Arrays.toString(args),userId,remoteAddr);
        // 执行目标方法
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        return result;
//        logger.info("Response from: {} {}.{}() with result = {} in {}ms", httpMethod, className, methodName, result, (endTime - startTime));
    }
}
