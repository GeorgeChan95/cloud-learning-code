package com.george.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 *     spring上下文配置
 * </p>
 *
 * @author George Chan
 * @version 1.0
 * @date 2020/3/21 20:58
 * @since JDK 1.8
 */
@Configuration
public class ApplicationContextConfig {
    /**
     * spring5 提供的bean，用户发送http请求
     * @return
     */
    @Bean
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }
}
