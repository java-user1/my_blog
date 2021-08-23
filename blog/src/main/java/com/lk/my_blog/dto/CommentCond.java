package com.lk.my_blog.dto;

import lombok.Data;

/**
 * @Author: 刘康
 * @Date: 2021/8/16 11:01
 * @Description:
 */
@Data
public class CommentCond {
    /**
     * 状态
     */
    private String status;
    /**
     * 开始时间戳
     */
    private Integer startTime;
    /**
     * 结束时间戳
     */
    private Integer endTime;

    /**
     * 父评论编号
     */
    private Integer parent;
}
