package com.lk.my_blog.dao.admin;

import com.lk.my_blog.model.RelationShip;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 刘康
 * @Date: 2021/8/16 11:47
 * @Description:
 */
@Mapper
@Repository
public interface RelationShipMapper {
    /**
     * 添加
     * @param relationShip
     * @return
     */
    int addRelationShip(RelationShip relationShip);

    /**
     * 根据文章编号和meta编号删除关联
     * @param cid
     * @param mid
     * @return
     */
    int deleteRelationShipById(@Param("cid") Integer cid, @Param("mid") Integer mid);

    /**
     * 根据文章编号删除关联
     * @param cid
     * @return
     */
    int deleteRelationShipByCid(@Param("cid") Integer cid);

    /**
     * 根据meta编号删除关联
     * @param mid
     * @return
     */
    int deleteRelationShipByMid(@Param("mid") Integer mid);

    /**
     * 更新
     * @param relationShip
     * @return
     */
    int updateRelationShip(RelationShip relationShip);

    /**
     * 根据文章主键获取关联
     * @param cid
     * @return
     */
    List<RelationShip> getRelationShipByCid(@Param("cid") Integer cid);

    /**
     * 根据meta编号获取关联
     * @param mid
     * @return
     */
    List<RelationShip> getRelationShipByMid(@Param("mid") Integer mid);

    /**
     * 获取数量
     * @param cid
     * @param mid
     * @return
     */
    Long getCountById(@Param("cid") Integer cid, @Param("mid") Integer mid);
}
