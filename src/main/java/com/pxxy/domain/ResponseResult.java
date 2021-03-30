package com.pxxy.domain;

import java.io.Serializable;

/**
 * @Descricption:用于和前台交互的json类
 * @Author:江灿
 * @Date:Create in 14:51 2019/5/29
 */
public class ResponseResult<T> implements Serializable {
    private int state; //状态码 0--成功  1--失败
    private String message; //状态描述信息
    private T data; //携带的数据（可选）


    public ResponseResult() {
        super();
    }


    public ResponseResult(int state) {
        super();
        this.state = state;
    }


    public ResponseResult(int state, String message) {
        super();
        this.state = state;
        this.message = message;
    }


    public ResponseResult(int state, String message, T data) {
        super();
        this.state = state;
        this.message = message;
        this.data = data;
    }


    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }


}

