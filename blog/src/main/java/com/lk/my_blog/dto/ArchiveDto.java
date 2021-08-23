package com.lk.my_blog.dto;

import com.lk.my_blog.model.Content;
import lombok.Data;

import java.util.List;

/**
 * 文章归档类
 * @author lk
 */
@Data
public class ArchiveDto {
    private String date;
    private String count;
    private List<Content> articles;
}
