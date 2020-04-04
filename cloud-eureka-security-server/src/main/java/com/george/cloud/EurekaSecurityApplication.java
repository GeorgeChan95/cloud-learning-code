package com.george.cloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <p>
 *     Eureka 注册中心 安全认证启动类
 * </p>
 *
 * @author George Chan
 * @version 1.0
 * @date 2020/4/4 20:33
 * @since JDK 1.8
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaSecurityApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(EurekaSecurityApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EurekaSecurityApplication.class, args);
        LOGGER.info("Eureka 注册中心启动成功......");
    }
}
