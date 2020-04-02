package com.george.cloud.result;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 *  分页对象
 * </p>
 *
 * @author GeorgeChan 2020/2/3 17:26
 * @version 1.0
 * @since jdk1.8
 */
@Data
@NoArgsConstructor // 无参构造器
public class PageResult<T> {
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
     * 分页数据具体信息
     */
    private List<T> rows;
    /**
     * 数据集合的总数
     */
    private long total;

    public PageResult(boolean flag, Integer code, String message, List<T> rows, long total) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.rows = rows;
        this.total = total;
    }
}
