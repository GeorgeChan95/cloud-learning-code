package com.george.cloud.annotation;

import java.lang.annotation.*;

/**
 * <p>
 *  自定义注解，用户获取当前登录用户
 * </p>
 *
 * @author GeorgeChan 2020/2/4 14:25
 * @version 1.0
 * @since jdk1.8
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestUser {
}
