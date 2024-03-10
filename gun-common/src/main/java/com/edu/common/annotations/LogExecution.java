package com.edu.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 注解应用于方法
@Retention(RetentionPolicy.RUNTIME) // 注解在运行时保留
public @interface LogExecution {
    // 可以定义注解的属性
}
