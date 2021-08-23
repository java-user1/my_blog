package com.lk.my_blog.dto;

import lombok.Data;

/**
 * @Author: 刘康
 * @Date: 2021/8/16 10:02
 * @Description: 文章查询条件
 */
@Data
public class ContentCond {
    /**
     * 标签
     */
    private String tag;
    /**
     * 类别
     */
    private String category;
    /**
     * 状态
     */
    private String status;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容匹配
     */
    private String content;
    /**
     * 文章类型
     */
    private String type;

    /**
     * 开始时间戳
     */
    private Integer startTime;

    /**
     * 结束时间戳
     */
    private Integer endTime;
}
