package com.lk.my_blog.service.admin.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lk.my_blog.constant.ErrorConstant;
import com.lk.my_blog.dao.admin.CommentMapper;
import com.lk.my_blog.dto.CommentCond;
import com.lk.my_blog.exception.BusinessException;
import com.lk.my_blog.model.Comment;
import com.lk.my_blog.model.Content;
import com.lk.my_blog.service.admin.CommentService;
import com.lk.my_blog.service.admin.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 评论实现类
 * Created by Donghua.Chen on 2018/4/29.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentDao;

    @Autowired
    private ContentService contentService;



    private static final Map<String,String> STATUS_MAP = new ConcurrentHashMap<>();

    /**
     * 评论状态：正常
     */
    private static final String STATUS_NORMAL = "approved";
    /**
     * 评论状态：不显示
     */
    private static final String STATUS_BLANK = "not_audit";

    static {
        STATUS_MAP.put("approved",STATUS_NORMAL);
        STATUS_MAP.put("not_audit",STATUS_BLANK);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @CacheEvict(value="commentCache",allEntries=true)
    public void addComment(Comment comments) {
        String msg = null;
        if (null == comments) {
            msg = "评论对象为空";
        }
        if(comments != null) {
            if (StringUtils.isBlank(comments.getAuthor())) {
                comments.setAuthor("热心网友");
            }
            if (StringUtils.isBlank(comments.getContent())) {
                msg = "评论内容不能为空";
            }
            if (comments.getContent().length() < 5 || comments.getContent().length() > 2000) {
                msg = "评论字数在5-2000个字符";
            }
            if (null == comments.getCid()) {
                msg = "评论文章不能为空";
            }
            if (msg != null){
                throw BusinessException.withErrorCode(msg);
            }
            Content article = contentService.getArticleById(comments.getCid());
            if (null == article){
                throw BusinessException.withErrorCode("该文章不存在");
            }
            comments.setOwnerId(article.getAuthorId());
            comments.setStatus(STATUS_MAP.get(STATUS_BLANK));
            commentDao.addComment(comments);

            Content temp = new Content();
            temp.setCid(article.getCid());
            Integer count = article.getCommentsNum();
            if (null == count) {
                count = 0;
            }
            temp.setCommentsNum(count + 1);
            contentService.updateContentByCid(temp);
        }

    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    @CacheEvict(value="commentCache",allEntries=true)
    public void deleteComment(Integer coid) {
        if(null == coid){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        // 如果删除的评论存在子评论，一并删除
        //查找当前评论是否有子评论
        CommentCond commentCond = new CommentCond();
        commentCond.setParent(coid);
        Comment comment = commentDao.getCommentById(coid);
        List<Comment> childComments = commentDao.getCommentsByCond(commentCond);
        Integer count = 0;
        //删除子评论
        if (null != childComments && childComments.size() > 0){
            for (int i = 0; i < childComments.size(); i++) {
                commentDao.deleteComment(childComments.get(i).getCoid());
                count++;
            }
        }
        //删除当前评论
        commentDao.deleteComment(coid);
        count++;

        //更新当前文章的评论数
        Content Content = contentService.getArticleById(comment.getCid());
        if (null != Content
                && null != Content.getCommentsNum()
                && Content.getCommentsNum() != 0){
            Content.setCommentsNum(Content.getCommentsNum() - count);
            contentService.updateContentByCid(Content);
        }
    }

    @Override
    @CacheEvict(value="commentCache",allEntries=true)
    public void updateCommentStatus(Integer coid, String status) {
        if(null == coid){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        commentDao.updateCommentStatus(coid, status);
    }

    @Override
    @Cacheable(value = "commentCache", key = "'commentById_' + #p0")
    public Comment getCommentById(Integer coid) {
        if(null == coid){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        return commentDao.getCommentById(coid);
    }

    @Override
    @Cacheable(value = "commentCache", key = "'commentsByCId_' + #p0")
    public List<Comment> getCommentsByCId(Integer cid) {
        if(null == cid){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        return commentDao.getCommentsByCId(cid);
    }

    @Override
    @Cacheable(value = "commentCache", key = "'commentsByCond_' + #p1")
    public PageInfo<Comment> getCommentsByCond(CommentCond commentCond, int pageNum, int pageSize) {
        if(null == commentCond){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> comments = commentDao.getCommentsByCond(commentCond);
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        return pageInfo;
    }
}
