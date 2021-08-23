package com.lk.my_blog.model;

import lombok.Data;

/**
 * @Author: 刘康
 * @Date: 2021/8/16 11:47
 * @Description:
 */
@Data
public class RelationShip {

    /**
     * 文章主键编号
     */
    private Integer cid;
    /**
     * 项目编号
     */
    private Integer mid;
}
