package com.lk.my_blog.dao.admin;

import com.lk.my_blog.dto.AttAchDto;
import com.lk.my_blog.model.AttAch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 刘康
 * @Date: 2021/8/17 10:47
 * @Description:
 */
@Mapper
@Repository
public interface AttAchMapper {
    /**
     * 添加单个附件信息
     * @param AttAch
     * @return
     */
    int addAttAch(AttAch attAch);

    /**
     * 批量添加附件信息
     * @param list
     * @return
     */
    int batchAddAttAch(List<AttAch> list);

    /**
     * 根据主键编号删除附件信息
     * @param id
     * @return
     */
    int deleteAttAch(int id);

    /**
     * 更新附件信息
     * @param AttAch
     * @return
     */
    int updateAttAch(AttAch attAch);

    /**
     * 根据主键获取附件信息
     * @param id
     * @return
     */
    AttAchDto getAttAchById(@Param("id") int id);

    /**
     * 获取所有的附件信息
     * @return
     */
    List<AttAchDto> getAtts();

    /**
     * 查找附件的数量
     * @return
     */
    Long getAttsCount();
}
