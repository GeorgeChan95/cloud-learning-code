package com.george.cloud.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author GeorgeChan 2020/2/4 14:19
 * @version 1.0
 * @since jdk1.8
 */
@Data
public class RequestSubject implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 登陆账户
     */
    private String account;
    /**
     * 角色编码
     */
    private List<String> roles;
    /**
     * 是否是手机登录
     */
    private boolean app;
}
