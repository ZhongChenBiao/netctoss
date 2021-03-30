package com.pxxy.service.ex;

/**
 * @Descricption:账单异常处理类
 * @Author:江灿
 * @Date:Create in 15:07 2019/5/29
 */
public class BillServiceException extends RuntimeException {
    public BillServiceException() {
        super();
    }

    public BillServiceException(String message) {
        super(message);
    }

    public BillServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BillServiceException(Throwable cause) {
        super(cause);
    }

    public BillServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
