package com.george.cloud.service;

import com.george.cloud.entity.User;
import com.george.cloud.enums.CommonStateEnums;
import com.george.cloud.result.Result;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

/**
 * <p></p>
 *
 * @author George Chan
 * @version 1.0
 * @date 2020/4/7 22:35
 * @since JDK 1.8
 */
@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.provider-service}")
    private String providerServiceUrl;

    /**
     * 使用 User id用户user对象
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "getDefaultUser")
    public Result getUser(Long id) {
        return restTemplate.getForObject(providerServiceUrl + "/user/{1}", Result.class, id);
    }

    /**
     * 设置 命令、分组、线程池
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "getDefaultUser",
            commandKey = "getUserCommand",
            groupKey = "getUserGroup",
            threadPoolKey = "getUserThreadPool")
    public Result getUserCommand(Long id) {
        return restTemplate.getForObject(providerServiceUrl + "/user/{1}", Result.class, id);
    }

    /**
     * ignoreException 测试
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "getDefaultUser2", ignoreExceptions = {NullPointerException.class})
    public Result getUserException(Long id) {
        if (id == 1) {
            throw new IndexOutOfBoundsException();
        } else if (id == 2) {
            throw new NullPointerException();
        }
        return restTemplate.getForObject(providerServiceUrl + "/user/{1}", Result.class, id);
    }

    /**
     * 降级触发
     * @param id
     * @return
     */
    public Result getDefaultUser2(@PathVariable Long id, Throwable e) {
        LOGGER.info("getUser 方法失败， 触发了降级策略.....");
        LOGGER.error("getDefaultUser2 id:{},throwable class:{}", id, e.getClass());
        User user = new User(100L, "Hystrix", "555");
        return new Result(false, CommonStateEnums.ERROR.getCode(), CommonStateEnums.ERROR.getMessage(), user);
    }

    /**
     * 测试使用 Hystrix 缓存
     * @param id
     * @return
     */
    @CacheResult(cacheKeyMethod = "getCacheKey")
    @HystrixCommand(fallbackMethod = "getDefaultUser", commandKey = "getUserCache")
    public Result getUserCache(Long id) {
        LOGGER.info("getUserCache id:{}", id);
        return restTemplate.getForObject(providerServiceUrl + "/user/{1}", Result.class, id);
    }

    /**
     * 为缓存生成key的方法
     */
    public String getCacheKey(Long id) {
        return String.valueOf(id);
    }


    /**
     * 降级触发
     * @param id
     * @return
     */
    public Result getDefaultUser(@PathVariable Long id) {
        LOGGER.info("getUser 方法失败， 触发了降级策略.....");
        User user = new User(100L, "Hystrix", "555");
        return new Result(false, CommonStateEnums.ERROR.getCode(), CommonStateEnums.ERROR.getMessage(), user);
    }

    /**
     * 移除 hystrix缓存
     * @param id
     */
    @CacheRemove(commandKey = "getUserCache", cacheKeyMethod = "getCacheKey")
    @HystrixCommand
    public Result removeCache(Long id) {
        LOGGER.info("removeCache id:{}", id);
        return restTemplate.getForObject(providerServiceUrl + "/user/{1}", Result.class, id);
    }
}
