package com.george.cloud.controller;

import com.george.cloud.entity.User;
import com.george.cloud.enums.CommonStateEnums;
import com.george.cloud.result.Result;
import com.george.cloud.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *     User 前端控制器
 * </p>
 *
 * @author George Chan
 * @version 1.0
 * @date 2020/4/4 23:23
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public Result create(@RequestBody User user) {
        userService.create(user);
        return new Result(true, CommonStateEnums.SUCCESS.getCode(), CommonStateEnums.SUCCESS.getMessage());
    }

    @GetMapping("/{id}")
    public Result getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        LOGGER.info("根据id获取用户信息，用户名称为：{}", user.getUsername());
        return new Result(true, CommonStateEnums.SUCCESS.getCode(), CommonStateEnums.SUCCESS.getMessage(), user);
    }

    @GetMapping("/getUserByIds")
    public Result getUserByIds(@RequestParam List<Long> ids) {
        List<User> userList = userService.getUserByIds(ids);
        LOGGER.info("根据ids获取用户信息，用户列表为：{}", userList);
        return new Result(true, CommonStateEnums.SUCCESS.getCode(), CommonStateEnums.SUCCESS.getMessage(), userList);
    }

    @GetMapping("/getByUsername")
    public Result getByUsername(@RequestParam String username) {
        User user = userService.getByUsername(username);
        return new Result(true, CommonStateEnums.SUCCESS.getCode(), CommonStateEnums.SUCCESS.getMessage(), user);
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return new Result(true, CommonStateEnums.SUCCESS.getCode(), CommonStateEnums.SUCCESS.getMessage());
    }

    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable Long id) {
        userService.delete(id);
        return new Result(true, CommonStateEnums.SUCCESS.getCode(), CommonStateEnums.SUCCESS.getMessage());
    }
}
