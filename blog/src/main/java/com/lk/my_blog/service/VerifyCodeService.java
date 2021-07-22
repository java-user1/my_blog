package com.lk.my_blog.service;


import com.lk.my_blog.model.VerifyCode;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author 刘康
 * 验证码生成接口
 */
public interface VerifyCodeService {
    /**
     * 验证码生成并返回code,将图片写入os中
     * @param width
     * @param height
     * @param os
     * @return
     */
    String generate(int width, int height, OutputStream os) throws IOException;
    /**
     * 生成验证码对象
     * @param width
     * @param height
     * @return
     */
    VerifyCode generate(int width, int height);
}
