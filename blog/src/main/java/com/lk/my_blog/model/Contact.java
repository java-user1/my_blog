package com.lk.my_blog.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.io.Serializable;

/**
 * @Author: 刘康
 * @Date: 2021/7/30 9:30
 * @Description: 联系我实体类
 */
@Data
public class Contact implements Serializable {
    private Integer id;
    private String visitorName;
    private String email;
    private String subject;
    private String message;
}
