package com.lk.my_blog.dto;

import lombok.Data;

/**
 * @Author: 刘康
 * @Date: 2021/8/16 11:11
 * @Description: 分类标签查询条件
 */
@Data
public class MetaCond {
    /**
     * meta Name
     */
    private String name;
    /**
     * 类型
     */
    private String type;
}
