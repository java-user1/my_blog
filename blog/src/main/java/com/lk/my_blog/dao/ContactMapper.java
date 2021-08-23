package com.lk.my_blog.dao;

import com.lk.my_blog.model.Contact;
import com.lk.my_blog.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 刘康
 * @Date: 2021/7/30 9:34
 * @Description:
 */
@Mapper
@Repository
public interface ContactMapper {
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
