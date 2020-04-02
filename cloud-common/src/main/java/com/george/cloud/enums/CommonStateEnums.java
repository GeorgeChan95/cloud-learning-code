package com.george.cloud.enums;

/**
 * <p>
 *     返回状态枚举类
 * </p>
 *
 * @author George Chan
 * @version 1.0
 * @date 2020/3/21 20:36
 * @since JDK 1.8
 */
public enum CommonStateEnums {
    SUCCESS(20000, "操作成功！"),
    ERROR(20001, "操作失败！"),
    LOGIN_ERROR(20002, "用户名或密码错误！"),
    ACCESS_ERROR(20003, "权限不足！"),
    REMOTE_ERROR(20004, "远程调用失败！"),
    REP_ERROR(20005, "重复操作！")
    ;
    private final Integer code;
    private final String message;

    CommonStateEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
