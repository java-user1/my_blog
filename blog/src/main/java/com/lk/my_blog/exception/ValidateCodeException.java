package com.lk.my_blog.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author: 刘康
 * @Date: 2021/6/6 15:47
 * @Description: 验证码异常类
 */
public class ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID=5022575393500654458L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
