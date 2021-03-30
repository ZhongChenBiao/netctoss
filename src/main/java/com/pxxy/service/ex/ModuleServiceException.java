package com.pxxy.service.ex;

/**
 * @Descricption:权限的异常处理类
 * @Author:江灿
 * @Date:Create in 15:08 2019/5/29
 */
public class ModuleServiceException extends RuntimeException {
    public ModuleServiceException() {
        super();
    }

    public ModuleServiceException(String message) {
        super(message);
    }

    public ModuleServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModuleServiceException(Throwable cause) {
        super(cause);
    }

    public ModuleServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
