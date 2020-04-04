package com.george.cloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p>
 *     Ribbon 服务提供者启动类
 * </p>
 *
 * @author George Chan
 * @version 1.0
 * @date 2020/4/4 23:09
 * @since JDK 1.8
 */
@EnableDiscoveryClient
@SpringBootApplication
public class RibbonProviderApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(RibbonProviderApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RibbonProviderApplication.class, args);
        LOGGER.info("Ribbon Provider 启动成功......");
    }
}
