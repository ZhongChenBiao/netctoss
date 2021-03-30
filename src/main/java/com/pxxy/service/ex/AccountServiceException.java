package com.pxxy.service.ex;

/**
 * @Descricption:处理账户操作的异常处理类
 * @Author:江灿
 * @Date:Create in 15:02 2019/5/29
 */
public class AccountServiceException extends RuntimeException {
    public AccountServiceException() {
        super();
    }

    public AccountServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AccountServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountServiceException(String message) {
        super(message);
    }

    public AccountServiceException(Throwable cause) {
        super(cause);
    }
}

