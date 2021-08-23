package com.lk.my_blog.dto;

import com.lk.my_blog.model.Meta;
import lombok.Data;

/**
 * 标签、分类列表
 * @author lk
 */
@Data
public class MetaDto extends Meta {

    private int count;
}
