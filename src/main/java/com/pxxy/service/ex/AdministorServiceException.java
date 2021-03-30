package com.pxxy.service.ex;

/**
 * @Descricption:管理员操作异常处理类
 * @Author:江灿
 * @Date:Create in 15:04 2019/5/29
 */
public class AdministorServiceException extends RuntimeException {
    public AdministorServiceException() {
        super();
    }

    public AdministorServiceException(String message) {
        super(message);
    }

    public AdministorServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdministorServiceException(Throwable cause) {
        super(cause);
    }

    public AdministorServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
