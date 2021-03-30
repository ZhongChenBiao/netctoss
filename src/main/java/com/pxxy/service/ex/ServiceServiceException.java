package com.pxxy.service.ex;

/**
 * @Descricption:业务账号的异常处理类
 * @Author:江灿
 * @Date:Create in 15:10 2019/5/29
 */
public class ServiceServiceException extends RuntimeException {
    public ServiceServiceException() {
        super();
    }

    public ServiceServiceException(String message) {
        super(message);
    }

    public ServiceServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
