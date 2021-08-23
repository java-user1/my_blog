package com.lk.my_blog.dto;

import lombok.Data;

/**
 * @Author: 刘康
 * @Date: 2021/8/21 10:32
 * @Description:
 */
@Data
public class StatisticsDto {
    /**
     * 文章数
     */
    private Long articles;
    /**
     * 评论数
     */
    private Long comments;
    /**
     * 连接数
     */
    private Long links;
    /**
     * 留言数
     */
    private Long contact;
}
