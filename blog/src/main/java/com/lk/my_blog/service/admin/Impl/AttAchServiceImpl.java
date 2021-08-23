package com.lk.my_blog.service.admin.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lk.my_blog.constant.ErrorConstant;
import com.lk.my_blog.dao.admin.AttAchMapper;
import com.lk.my_blog.dto.AttAchDto;
import com.lk.my_blog.exception.BusinessException;
import com.lk.my_blog.model.AttAch;
import com.lk.my_blog.service.admin.AttAchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 刘康
 * @Date: 2021/8/17 10:52
 * @Description:
 */
@Service
public class AttAchServiceImpl implements AttAchService {
    @Autowired
    private AttAchMapper attAchDao;

    @Override
    @CacheEvict(value={"attCaches","attCache"},allEntries=true,beforeInvocation=true)
    public void addAttAch(AttAch attAch) {
        if (null == attAch){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        attAchDao.addAttAch(attAch);

    }

    @Override
    @CacheEvict(value={"attCaches","attCache"},allEntries=true,beforeInvocation=true)
    public void batchAddAttAch(List<AttAch> list) {
        if (null == list || list.size() == 0){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        attAchDao.batchAddAttAch(list);

    }

    @Override
    @CacheEvict(value={"attCaches","attCache"},allEntries=true,beforeInvocation=true)
    public void deleteAttAch(Integer id) {
        if (null == id){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        attAchDao.deleteAttAch(id);

    }

    @Override
    @CacheEvict(value={"attCaches","attCache"},allEntries=true,beforeInvocation=true)
    public void updateAttAch(AttAch attAch) {
        if (null == attAch || null == attAch.getId()){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        attAchDao.updateAttAch(attAch);

    }

    @Override
    @Cacheable(value = "attCache", key = "'attAchById' + #p0")
    public AttAchDto getAttAchById(Integer id) {
        if (null == id){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        return attAchDao.getAttAchById(id);
    }

    @Override
    @Cacheable(value = "attCaches", key = "'atts' + #p0")
    public PageInfo<AttAchDto> getAtts(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AttAchDto> atts = attAchDao.getAtts();
        PageInfo<AttAchDto> pageInfo = new PageInfo<>(atts);
        return pageInfo;
    }
}
