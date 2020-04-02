package com.george.cloud.result;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *  返回结果对象
 * </p>
 *
 * @author GeorgeChan 2020/2/3 17:24
 * @version 1.0
 * @since jdk1.8
 */
@Data
@NoArgsConstructor  // 无参构造器
public class Result {
    /**
     * 返回标记： true 或 false
     */
    private boolean flag;
    /**
     * 返回编码
     */
    private Integer code;
    /**
     * 返回文字信息
     */
    private String message;
    /**
     * 返回具体数据
     */
    private Object data;

    /**
     * 没有数据返回
     * @param flag
     * @param code
     * @param message
     */
    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    /**
     * 有具体数据返回
     * @param flag
     * @param code
     * @param message
     * @param data
     */
    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
