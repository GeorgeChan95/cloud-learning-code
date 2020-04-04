package com.george.cloud.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <p>
 *     Eureka Security 配置类
 * </p>
 *
 * @author George Chan
 * @version 1.0
 * @date 2020/4/4 20:39
 * @since JDK 1.8
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 设置不拦截的 uri
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }
}
