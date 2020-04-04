package com.george.cloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <p>
 *     Eureka注册中心启动类
 * </p>
 *
 * @author George Chan
 * @version 1.0
 * @date 2020/4/4 15:06
 * @since JDK 1.8
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(EurekaServerApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
        LOGGER.info("eureka注册中心启动成功......");
    }
}
