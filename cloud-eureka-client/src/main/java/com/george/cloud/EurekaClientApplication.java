package com.george.cloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p></p>
 *
 * @author George Chan
 * @version 1.0
 * @date 2020/4/4 16:38
 * @since JDK 1.8
 */
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(EurekaClientApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
        LOGGER.info("Eureka Client 启动成功......");
    }
}
