package com.lk.my_blog.dao.admin;

import com.lk.my_blog.dto.CommentCond;
import com.lk.my_blog.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 刘康
 * @Date: 2021/8/16 10:59
 * @Description:
 */
@Mapper
@Repository
public interface CommentMapper {
    /**
     * 新增评论
     * @param Comment
     * @return
     */
    int addComment(Comment Comment);

    /**
     * 删除评论
     * @param coid
     * @return
     */
    int deleteComment(@Param("coid") Integer coid);

    /**
     * 更新评论的状态
     * @param coid
     * @return
     */
    int updateCommentStatus(@Param("coid") Integer coid, @Param("status") String status);

    /**
     * 获取单条评论
     * @param coid
     * @return
     */
    Comment getCommentById(@Param("coid") Integer coid);
    /**
     * 根据文章编号获取评论列表
     * @param cid
     * @return
     */
    List<Comment> getCommentsByCId(@Param("cid") Integer cid);

    /**
     * 根据条件获取评论列表
     * @param commentCond
     * @return
     */
    List<Comment> getCommentsByCond(CommentCond commentCond);

    /**
     * 获取文章数量
     * @return
     */
    Long getCommentsCount();
}
