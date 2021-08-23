package com.lk.my_blog.exception;

import com.lk.my_blog.model.RespBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * 统一异常类
 * Created by Donghua.Chen on 2018/4/20.
 */
public class BusinessException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(BusinessException.class);
    protected String errorCode;
    protected String[] errorMessageArguments;
    protected RespBean respBean;

    protected BusinessException() {
        this("");
    }

    public BusinessException(String message) {
        super(message);
        this.errorCode = "fail";
        this.errorMessageArguments = new String[0];
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "fail";
        this.errorMessageArguments = new String[0];
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String[] getErrorMessageArguments() {
        return this.errorMessageArguments;
    }

    public void setErrorMessageArguments(String[] errorMessageArguments) {
        this.errorMessageArguments = errorMessageArguments;
    }

    public static BusinessException withErrorCode(String errorCode) {
        BusinessException businessException = new BusinessException();
        businessException.errorCode = errorCode;
        return businessException;
    }

    public static BusinessException fromRespBean(RespBean respBean) {
        BusinessException businessException = new BusinessException();
        if(respBean == null) {
            respBean = RespBean.error("NULL");
        }

        businessException.respBean = respBean;
        return businessException;
    }

    public BusinessException withErrorMessageArguments(String... errorMessageArguments) {
        if(errorMessageArguments != null) {
            this.errorMessageArguments = errorMessageArguments;
        }

        return this;
    }
    public RespBean response() {
        if(this.respBean != null) {
            return this.respBean;
        } else {
            this.respBean = RespBean.error(this.getErrorCode());
            if(this.getErrorMessageArguments() != null && this.getErrorMessageArguments().length > 0) {
                try {
                    this.respBean.setMsg(MessageFormat.format(this.respBean.getMsg(), this.getErrorMessageArguments()));
                } catch (Exception var2) {
                    logger.error(var2.getMessage());
                }
            }

            return this.respBean;
        }
    }

}
