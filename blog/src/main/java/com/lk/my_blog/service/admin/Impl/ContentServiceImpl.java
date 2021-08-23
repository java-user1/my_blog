package com.lk.my_blog.service.admin.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lk.my_blog.constant.ErrorConstant;
import com.lk.my_blog.constant.Types;
import com.lk.my_blog.constant.WebConst;
import com.lk.my_blog.dao.admin.CommentMapper;
import com.lk.my_blog.dao.admin.ContentMapper;
import com.lk.my_blog.dao.admin.RelationShipMapper;
import com.lk.my_blog.dto.ContentCond;
import com.lk.my_blog.exception.BusinessException;
import com.lk.my_blog.model.Comment;
import com.lk.my_blog.model.Content;
import com.lk.my_blog.model.RelationShip;
import com.lk.my_blog.model.RespBean;
import com.lk.my_blog.service.admin.ContentService;
import com.lk.my_blog.service.admin.MetaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Donghua.Chen on 2018/4/29.
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private CommentMapper commentDao;

    @Autowired
    private MetaService metaService;

    @Autowired
    private RelationShipMapper relationShipDao;


    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    @CacheEvict(value={"atricleCache","atricleCaches"},allEntries=true,beforeInvocation=true)
    public void addArticle(Content content) {
        if(null == content){
             throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        if(StringUtils.isBlank(content.getTitle())){
            throw BusinessException.withErrorCode(ErrorConstant.Article.TITLE_CAN_NOT_EMPTY);}
        if(content.getTitle().length() > WebConst.MAX_TITLE_COUNT){
            throw BusinessException.withErrorCode(ErrorConstant.Article.TITLE_IS_TOO_LONG);
        }
        if(StringUtils.isBlank(content.getContent())){
            throw BusinessException.withErrorCode(ErrorConstant.Article.CONTENT_CAN_NOT_EMPTY);
        }
        if(content.getContent().length() > WebConst.MAX_TEXT_COUNT){
            throw BusinessException.withErrorCode(ErrorConstant.Article.CONTENT_IS_TOO_LONG);
        }

        //标签和分类
        String tags = content.getTags();
        String categories = content.getCategories();

        contentMapper.addArticle(content);
        int cid = content.getCid();
        metaService.addMetas(cid, tags, Types.TAG.getType());
        metaService.addMetas(cid, categories, Types.CATEGORY.getType());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @CacheEvict(value = {"atricleCache", "atricleCaches"}, allEntries = true, beforeInvocation = true)
    public void deleteArticleById(Integer cid) {
        if(null == cid){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        contentMapper.deleteArticleById(cid);
        //同时也要删除该文章下的所有评论
        List<Comment> comments = commentDao.getCommentsByCId(cid);
        if (null != comments && comments.size() > 0) {
            comments.forEach(comment -> {
                commentDao.deleteComment(comment.getCoid());
            });
        }
        //删除标签和分类关联
        List<RelationShip> relationShips = relationShipDao.getRelationShipByCid(cid);
        if (null != relationShips && relationShips.size() > 0) {
            relationShipDao.deleteRelationShipByCid(cid);
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @CacheEvict(value={"atricleCache","atricleCaches"},allEntries=true,beforeInvocation=true)
    public void updateArticleById(Content Content) {
        //标签和分类
        String tags = Content.getTags();
        String categories = Content.getCategories();

        contentMapper.updateArticleById(Content);
        int cid = Content.getCid();
        relationShipDao.deleteRelationShipByCid(cid);
        metaService.addMetas(cid, tags, Types.TAG.getType());
        metaService.addMetas(cid, categories, Types.CATEGORY.getType());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @CacheEvict(value={"atricleCache","atricleCaches"},allEntries=true,beforeInvocation=true)
    public void updateCategory(String ordinal, String newCatefory) {
        ContentCond cond = new ContentCond();
        cond.setCategory(ordinal);
        List<Content> atricles = contentMapper.getArticlesByCond(cond);
        atricles.forEach(atricle -> {
            atricle.setCategories(atricle.getCategories().replace(ordinal, newCatefory));
            contentMapper.updateArticleById(atricle);
        });
    }



    @Override
    @CacheEvict(value={"atricleCache","atricleCaches"},allEntries=true,beforeInvocation=true)
    public void updateContentByCid(Content content) {
        if (null != content && null != content.getCid()) {
            contentMapper.updateArticleById(content);
        }
    }

    @Override
    @Cacheable(value = "atricleCache", key = "'atricleById_' + #p0")
    public Content getArticleById(Integer cid) {
        return contentMapper.getArticleById(cid);
    }

    @Override
    @Cacheable(value = "atricleCaches", key = "'articlesByCond_' + #p1 + 'type_' + #p0.type")
    public PageInfo<Content> getArticlesByCond(ContentCond contentCond, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Content> contents = contentMapper.getArticlesByCond(contentCond);
        PageInfo<Content> pageInfo = new PageInfo<>(contents);
        return pageInfo;
    }

    @Override
    @Cacheable(value = "atricleCaches", key = "'recentlyArticle_' + #p0")
    public PageInfo<Content> getRecentlyArticle(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Content> recentlyArticle = contentMapper.getRecentlyArticle();
        PageInfo<Content> pageInfo = new PageInfo<>(recentlyArticle);
        return pageInfo;
    }

    @Override
    public PageInfo<Content> searchArticle(String param, int pageNun, int pageSize) {
        PageHelper.startPage(pageNun,pageSize);
        List<Content> Contents = contentMapper.searchArticle(param);
        PageInfo<Content> pageInfo = new PageInfo<>(Contents);
        return pageInfo;
    }
}
