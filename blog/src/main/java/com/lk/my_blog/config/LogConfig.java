package com.lk.my_blog.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 刘康
 * @Date: 2021/7/21 12:20
 * @Description: 配置日志
 */
@Configuration
public class LogConfig {
    private static final Logger LOG= LoggerFactory.getLogger(LogConfig.class);

    public Logger getLog(){
        return LOG;
    }
}
