package com.lk.my_blog.service.admin.Impl;

import com.lk.my_blog.dao.ContactMapper;
import com.lk.my_blog.dao.admin.CommentMapper;
import com.lk.my_blog.dao.admin.ContentMapper;
import com.lk.my_blog.dto.StatisticsDto;
import com.lk.my_blog.service.ContactService;
import com.lk.my_blog.service.admin.BaseService;
import com.lk.my_blog.service.admin.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 刘康
 * @Date: 2021/8/21 10:37
 * @Description:
 */
@Service
public class BaseServiceImpl implements BaseService {
    @Autowired
    private ContactMapper contactMapper;

    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public StatisticsDto getStatistics() {
        StatisticsDto statisticsDto = new StatisticsDto();
        Long contacts = contactMapper.countContact();
        Long articleCount = contentMapper.getArticleCount();
        Long commentsCount = commentMapper.getCommentsCount();
        statisticsDto.setContact(contacts);
        statisticsDto.setArticles(articleCount);
        statisticsDto.setComments(commentsCount);
        return statisticsDto;
    }
}
