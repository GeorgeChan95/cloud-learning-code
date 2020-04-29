package com.george.cloud.controller;

import com.george.cloud.enums.CommonStateEnums;
import com.george.cloud.result.Result;
import com.george.cloud.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author George Chan
 * @version 1.0
 * @date 2020/4/7 22:37
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/hystrix")
public class HystrixUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HystrixUserController.class);

    @Autowired
    private UserService userService;

    /**
     * 测试 hystrix 降级策略
     * @param id 参数id
     * @return
     */
    @RequestMapping(value = "/testFallback/{id}", method = RequestMethod.GET)
    public Result testFallback(@PathVariable Long id) {
        return userService.getUser(id);
    }

    /**
     * 测试 命令、分组、线程池名称
     * @param id user id
     * @return
     */
    @RequestMapping(value = "/testCommand/{id}", method = RequestMethod.GET)
    public Result testCommand(@PathVariable Long id) {
        return userService.getUserCommand(id);
    }

    /**
     * 测试 ignoreException 忽略某些异常降级
     * @param id user id
     * @return
     */
    @RequestMapping(value = "/testException/{id}", method = RequestMethod.GET)
    public Result testException(@PathVariable Long id) {
        return userService.getUserException(id);
    }

    /**
     * 测试使用 Hystrix缓存
     * @param id
     * @return
     */
    @RequestMapping(value = "/testCache/{id}", method = RequestMethod.GET)
    public Result testCache(@PathVariable Long id) {
        userService.getUserCache(id);
        userService.getUserCache(id);
        userService.getUserCache(id);
        return new Result(true, CommonStateEnums.SUCCESS.getCode(), CommonStateEnums.SUCCESS.getMessage());
    }

    /**
     * 移除 Hystrix 缓存
     * @param id
     * @return
     */
    @RequestMapping(value = "/testRemoveCache/{id}", method = RequestMethod.GET)
    public Result testRemoveCache(@PathVariable Long id) {
        userService.getUserCache(id);
        userService.removeCache(id);
        userService.getUserCache(id);
        return new Result(true, CommonStateEnums.SUCCESS.getCode(), CommonStateEnums.SUCCESS.getMessage());
    }
}
