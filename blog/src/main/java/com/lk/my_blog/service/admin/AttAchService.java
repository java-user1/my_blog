package com.lk.my_blog.service.admin;

import com.github.pagehelper.PageInfo;
import com.lk.my_blog.dto.AttAchDto;
import com.lk.my_blog.model.AttAch;

import java.util.List;

/**
 * @Author: 刘康
 * @Date: 2021/8/17 10:50
 * @Description: 文件功能管理
 */
public interface AttAchService {
    /**
     * 添加单个附件信息
     * @param attAch
     * @return
     */
    void addAttAch(AttAch attAch);

    /**
     * 批量添加附件信息
     * @param list
     * @return
     */
    void batchAddAttAch(List<AttAch> list);

    /**
     * 根据主键编号删除附件信息
     * @param id
     * @return
     */
    void deleteAttAch(Integer id);

    /**
     * 更新附件信息
     * @param attAch
     * @return
     */
    void updateAttAch(AttAch attAch);

    /**
     * 根据主键获取附件信息
     * @param id
     * @return
     */
    AttAchDto getAttAchById(Integer id);

    /**
     * 获取所有的附件信息
     * @return
     */
    PageInfo<AttAchDto> getAtts(int pageNum, int pageSize);
}
