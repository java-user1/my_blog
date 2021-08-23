package com.lk.my_blog.model;

import lombok.Data;

/**
 * @Author: 刘康
 * @Date: 2021/8/17 10:46
 * @Description:
 */
@Data
public class AttAch {
    /** 主键编号 */
    private Integer id;
    /** 文件名称 */
    private String fname;
    /** 文件类型 */
    private String ftype;
    /** 文件的地址 */
    private String fkey;
    /** 创建人的id */
    private Integer authorId;
    /** 创建的时间戳 */
    private Integer created;
}
