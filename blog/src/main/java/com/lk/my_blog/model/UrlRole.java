package com.lk.my_blog.model;

/**
 * @Author: 刘康
 * @Date: 2021/6/25 21:15
 * @Description: 地址和权限
 */
public class UrlRole {
    private Integer id;
    private Integer urlId;
    private Integer rid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUrlId() {
        return urlId;
    }

    public void setUrlId(Integer urlId) {
        this.urlId = urlId;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}
