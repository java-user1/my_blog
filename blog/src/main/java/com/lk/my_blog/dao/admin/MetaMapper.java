package com.lk.my_blog.dao.admin;

import com.lk.my_blog.dto.MetaCond;
import com.lk.my_blog.dto.MetaDto;
import com.lk.my_blog.model.Meta;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: 刘康
 * @Date: 2021/8/16 11:09
 * @Description:
 */
@Mapper
@Repository
public interface MetaMapper {
    /**
     * 添加项目
     * @param meta
     * @return
     */
    int addMeta(Meta meta);

    /**
     * 删除项目
     * @param mid
     * @return
     */
    int deleteMetaById(@Param("mid") Integer mid);

    /**
     * 更新项目
     * @param meta
     * @return
     */
    int updateMeta(Meta meta);

    /**
     * 根据编号获取项目
     * @param mid
     * @return
     */
    Meta getMetaById(@Param("mid") Integer mid);


    /**
     * 根据条件查询
     * @param metaCond
     * @return
     */
    List<Meta> getMetasByCond(MetaCond metaCond);

    /**
     * 根据类型获取meta数量
     * @param type
     * @return
     */
    Long getMetasCountByType(@Param("type") String type);

    /**
     * 根据sql查询
     * @param paraMap
     * @return
     */
    List<MetaDto> selectFromSql(Map<String, Object> paraMap);
}
