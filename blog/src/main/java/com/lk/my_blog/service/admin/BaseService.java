package com.lk.my_blog.service.admin;

import com.lk.my_blog.dto.StatisticsDto;

/**
 * @Author: 刘康
 * @Date: 2021/8/21 10:31
 * @Description:
 */
public interface BaseService {

    /**
     * 后台数据统计
     * @return
     */
    StatisticsDto getStatistics();

}
