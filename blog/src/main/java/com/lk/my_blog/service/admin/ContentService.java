package com.lk.my_blog.service.admin;

import com.github.pagehelper.PageInfo;
import com.lk.my_blog.dto.ContentCond;
import com.lk.my_blog.model.Content;
import com.lk.my_blog.model.RespBean;

/**
 * 文章服务层
 * @author lk
 */
public interface ContentService {

    /**
     * 添加文章
     * @param content
     * @return 返回添加操作提示
     */
    void addArticle(Content content);

    /**
     * 根据编号删除文章
     * @param cid
     * @return
     */
    void deleteArticleById(Integer cid);

    /**
     * 更新文章
     * @param content
     * @return
     */
    void updateArticleById(Content content);

    /**
     * 更新分类
     * @param ordinal
     * @param newCatefory
     */
    void updateCategory(String ordinal, String newCatefory);



    /**
     * 添加文章点击量
     * @param content
     */
    void updateContentByCid(Content content);

    /**
     * 根据编号获取文章
     * @param cid
     * @return
     */
    Content getArticleById(Integer cid);

    /**
     * 根据条件获取文章列表
     * @param contentCond 文章查询条件
     * @param pageNum 文章编号
     * @param pageSize 文章数量
     * @return 查询到的所有文章
     */
    PageInfo<Content> getArticlesByCond(ContentCond contentCond, int pageNum, int pageSize);

    /**
     * 获取最近的文章（只包含id和title）
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Content> getRecentlyArticle(int pageNum, int pageSize);

    /**
     * 搜索文章
     * @param param
     * @param pageNun
     * @param pageSize
     * @return
     */
    PageInfo<Content> searchArticle(String param, int pageNun, int pageSize);


}
