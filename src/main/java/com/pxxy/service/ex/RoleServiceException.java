package com.pxxy.service.ex;

/**
 * @Descricption:角色操作的异常处理类
 * @Author:江灿
 * @Date:Create in 15:09 2019/5/29
 */
public class RoleServiceException extends RuntimeException {
    public RoleServiceException() {
        super();
    }

    public RoleServiceException(String message) {
        super(message);
    }

    public RoleServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleServiceException(Throwable cause) {
        super(cause);
    }

    public RoleServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
