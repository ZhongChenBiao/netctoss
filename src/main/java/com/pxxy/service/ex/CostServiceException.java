package com.pxxy.service.ex;

/**
 * @Descricption:账单异常处理类
 * @Author:江灿
 * @Date:Create in 15:07 2019/5/29
 */
public class CostServiceException extends RuntimeException {
    public CostServiceException() {
        super();
    }

    public CostServiceException(String message) {
        super(message);
    }

    public CostServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CostServiceException(Throwable cause) {
        super(cause);
    }

    public CostServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
