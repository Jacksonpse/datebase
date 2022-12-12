package com.example.safefdu.annoation;

import java.lang.annotation.*;

/**
 * 自定义注解@RoleCheckq
 * 拦截controller层的用户权限操作
 * 仅能作用于方法上
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface StuIdCheck {
}
