package com.george.cloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 用户实体类
 * </p>
 *
 * @author George Chan
 * @version 1.0
 * @date 2020/4/4 23:19
 * @since JDK 1.8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
}
