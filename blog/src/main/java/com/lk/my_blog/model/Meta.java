package com.lk.my_blog.model;

import lombok.Data;

/**
 * @Author: 刘康
 * @Date: 2021/8/16 11:08
 * @Description: 分类
 */
@Data
public class Meta {
    private static final long serialVersionUID = 1L;

    /**
     * 项目主键
     */
    private Integer mid;

    /**
     * 名称
     */
    private String name;

    /**
     * 项目缩略名
     */
    private String slug;

    /**
     * 项目类型
     */
    private String type;

    /**
     * 对应的文章类型
     */
    private String contentType;

    /**
     * 选项描述
     */
    private String description;

    /**
     * 项目排序
     */
    private Integer sort;

    private Integer parent;
}
