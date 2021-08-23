package com.lk.my_blog.service.admin.Impl;


import com.lk.my_blog.constant.ErrorConstant;
import com.lk.my_blog.constant.Types;
import com.lk.my_blog.constant.WebConst;
import com.lk.my_blog.dao.admin.MetaMapper;
import com.lk.my_blog.dao.admin.RelationShipMapper;
import com.lk.my_blog.dto.MetaCond;
import com.lk.my_blog.dto.MetaDto;
import com.lk.my_blog.exception.BusinessException;
import com.lk.my_blog.model.Content;
import com.lk.my_blog.model.Meta;
import com.lk.my_blog.model.RelationShip;
import com.lk.my_blog.service.admin.ContentService;
import com.lk.my_blog.service.admin.MetaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Donghua.Chen on 2018/4/29.
 */
@Service
@Transactional
public class MetaServiceImpl implements MetaService {

    @Autowired
    private MetaMapper metaDao;

    @Autowired
    private RelationShipMapper relationShipDao;


    @Autowired
    private ContentService contentService;

    @Override
    @CacheEvict(value={"metaCaches","metaCache"},allEntries=true,beforeInvocation=true)
    public void addMeta(Meta meta) {
        if(null == meta){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        metaDao.addMeta(meta);

    }

    @Override
    @CacheEvict(value={"metaCaches","metaCache"},allEntries=true,beforeInvocation=true)
    public void saveMeta(String type, String name, Integer mid) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(name)){
            MetaCond metaCond = new MetaCond();
            metaCond.setName(name);
            metaCond.setType(type);
            List<Meta> metas = metaDao.getMetasByCond(metaCond);
            if (null == metas || metas.size() == 0){
                Meta Meta = new Meta();
                Meta.setName(name);
                if (null != mid){
                    Meta meta = metaDao.getMetaById(mid);
                    if(null != meta){
                        Meta.setMid(mid);
                    }

                    metaDao.updateMeta(Meta);
                    //更新原有的文章分类
                    if(meta !=null) {
                        contentService.updateCategory(meta.getName(), name);
                    }
                } else {
                    Meta.setType(type);
                    metaDao.addMeta(Meta);
                }
            } else {
                throw BusinessException.withErrorCode(ErrorConstant.Meta.META_IS_EXIST);

            }

        }
    }

    @Override
    @CacheEvict(value={"metaCaches","metaCache"},allEntries=true,beforeInvocation=true)
    public void addMetas(Integer cid, String names, String type) {
        if(null == cid){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }

        if (StringUtils.isNotBlank(names) && StringUtils.isNotBlank(type)) {
            String[] nameArr = StringUtils.split(names, ",");
            for (String name : nameArr) {
                this.saveOrUpdate(cid, name, type);
            }
        }
    }

    @Override
    @CacheEvict(value={"metaCaches","metaCache"},allEntries=true,beforeInvocation=true)
    public void saveOrUpdate(Integer cid, String name, String type) {
        MetaCond metaCond = new MetaCond();
        metaCond.setName(name);
        metaCond.setType(type);
        List<Meta> metas = this.getMetas(metaCond);

        int mid;
        Meta Meta;
        if (metas.size() == 1){
            Meta meta = metas.get(0);
            mid = meta.getMid();
        }else if (metas.size() > 1){
            throw BusinessException.withErrorCode(ErrorConstant.Meta.NOT_ONE_RESULT);
        } else {
            Meta = new Meta();
            Meta.setSlug(name);
            Meta.setName(name);
            Meta.setType(type);
            this.addMeta(Meta);
            mid = Meta.getMid();
        }
        if (mid != 0){
            Long count = relationShipDao.getCountById(cid, mid);
            if (count == 0){
                RelationShip relationShip = new RelationShip();
                relationShip.setCid(cid);
                relationShip.setMid(mid);
                relationShipDao.addRelationShip(relationShip);
            }

        }
    }

    @Override
    @CacheEvict(value={"metaCaches","metaCache"},allEntries=true,beforeInvocation=true)
    public void deleteMetaById(Integer mid) {
        if(null == mid) {
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        Meta meta = metaDao.getMetaById(mid);
        if (null != meta){
            String type = meta.getType();
            String name = meta.getName();
            metaDao.deleteMetaById(mid);
            //需要把相关的数据删除
            List<RelationShip> relationShips = relationShipDao.getRelationShipByMid(mid);
            if (null != relationShips && relationShips.size() > 0){
                for (RelationShip relationShip : relationShips) {
                    Content article = contentService.getArticleById(relationShip.getCid());
                    if (null != article){
                        Content temp = new Content();
                        temp.setCid(relationShip.getCid());
                        if (type.equals(Types.CATEGORY.getType())) {
                            temp.setCategories(reMeta(name, article.getCategories()));
                        }
                        if (type.equals(Types.TAG.getType())) {
                            temp.setTags(reMeta(name, article.getTags()));
                        }
                        //将删除的资源去除
                        contentService.updateArticleById(temp);
                    }
                }
                relationShipDao.deleteRelationShipByMid(mid);
            }
        }



    }

    @Override
    @CacheEvict(value={"metaCaches","metaCache"},allEntries=true,beforeInvocation=true)
    public void updateMeta(Meta meta) {
        if(null == meta || null == meta.getMid()){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        metaDao.updateMeta(meta);

    }

    @Override
    @Cacheable(value = "metaCache", key = "'metaById_' + #p0")
    public Meta getMetaById(Integer mid) {
        if(null == mid){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        return metaDao.getMetaById(mid);
    }

    @Override
    @Cacheable(value = "metaCaches", key = "'metas_' + #p0")
    public List<Meta> getMetas(MetaCond metaCond) {
        return metaDao.getMetasByCond(metaCond);
    }


    @Override
    @Cacheable(value = "metaCaches", key = "'metaList_' + #p0")
    public List<MetaDto> getMetaList(String type, String orderby, int limit) {
        if (StringUtils.isNotBlank(type)){
            if (StringUtils.isBlank(orderby)) {
                orderby = "count desc, a.mid desc";
            }
            if (limit < 1 || limit > WebConst.MAX_POSTS) {
                limit = 10;
            }
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("type", type);
            paraMap.put("order", orderby);
            paraMap.put("limit", limit);
            return metaDao.selectFromSql(paraMap);
        }
        return null;
    }

    private String reMeta(String name, String metas) {
        String[] ms = StringUtils.split(metas, ",");
        StringBuilder sbuf = new StringBuilder();
        for (String m : ms) {
            if (!name.equals(m)) {
                sbuf.append(",").append(m);
            }
        }
        if (sbuf.length() > 0) {
            return sbuf.substring(1);
        }
        return "";
    }
}
