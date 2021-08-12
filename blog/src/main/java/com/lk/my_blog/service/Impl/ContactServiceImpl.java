package com.lk.my_blog.service.Impl;

import com.lk.my_blog.config.LogConfig;
import com.lk.my_blog.dao.ContactMapper;
import com.lk.my_blog.model.Contact;
import com.lk.my_blog.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 刘康
 * @Date: 2021/7/30 10:04
 * @Description:
 */
@Service
public class ContactServiceImpl implements ContactService {


    @Autowired
    private ContactMapper contactMapper;

    private LogConfig logConfig;

    @Override
    public Contact getContactById(Integer id) {
        return contactMapper.getContactById(id);
    }

    @Override
    public Contact getContactByName(String name) {
        return contactMapper.getContactByName(name);
    }

    @Override
    public List<Contact> getAllContact() {
        return contactMapper.getAllContact();
    }

    @Override
    public int addContact(Contact contact) {
        return contactMapper.addContact(contact);
    }

    @Override
    public int deleteContactById(Integer id) {
        contactMapper.deleteContactById(id);
        return 1;
    }

    @Override
    public Long countContact() {
        Long num = contactMapper.countContact();
        return num;
    }
}
