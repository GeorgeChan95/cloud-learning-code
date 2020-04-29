package com.george.cloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p></p>
 *
 * @author George Chan
 * @version 1.0
 * @date 2020/4/7 22:22
 * @since JDK 1.8
 */
@EnableCircuitBreaker // 启动断路器功能
@EnableDiscoveryClient// 服务注册进入eureka
@SpringBootApplication
public class HystrixApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(HystrixApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HystrixApplication.class, args);
        LOGGER.info("cloud-hystrix-service 启动成功......");
    }
}
