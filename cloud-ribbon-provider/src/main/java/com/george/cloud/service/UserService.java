package com.george.cloud.service;

import com.george.cloud.entity.User;

import java.util.List;

/**
 * <p></p>
 *
 * @author George Chan
 * @version 1.0
 * @date 2020/4/4 23:20
 * @since JDK 1.8
 */
public interface UserService {
    void create(User user);

    User getUser(Long id);

    void update(User user);

    void delete(Long id);

    User getByUsername(String username);

    List<User> getUserByIds(List<Long> ids);
}
