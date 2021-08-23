package com.lk.my_blog.service;

import com.lk.my_blog.model.Contact;

import java.util.List;

/**
 * @Author: 刘康
 * @Date: 2021/7/30 10:03
 * @Description:
 */
public interface ContactService {
    /**
     * 根据用户id查询游客发送信息
     * @param id
     * @return Contact
     */
    Contact getContactById(Integer id);

    /**
     * 根据用户名查询角色信息
     * @param name
     * @return User
     */
    Contact getContactByName(String name);

    /**
     * 查询所有角色信息
     * @return
     */
    List<Contact> getAllContact();

    /**
     * 新增角色信息
     * @param contact
     * @return
     */
    int addContact(Contact contact);

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    int deleteContactById(Integer id);

    /**
     * 统计留言数量
     * @return
     */
    Long countContact();
}
