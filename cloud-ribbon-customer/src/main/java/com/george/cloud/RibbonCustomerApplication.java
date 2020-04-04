package com.george.cloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p>
 *     Ribbon 服务消费者启动类
 * </p>
 *
 * @author George Chan
 * @version 1.0
 * @date 2020/4/4 23:54
 * @since JDK 1.8
 */
@EnableDiscoveryClient
@SpringBootApplication
public class RibbonCustomerApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(RibbonCustomerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RibbonCustomerApplication.class, args);
        LOGGER.info("Ribbon Customer 启动成功.....");
    }
}
