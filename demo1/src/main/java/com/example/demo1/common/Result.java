package com.example.demo1.common;

import java.io.Serializable;

/**
 * 封装统一返回的接口
 *
 * @auther willi
 * @create-time 2019-03-20-15:29
 */


public class Result<T> implements Serializable{
    private int resultCode;
    private String msg;
    private T data;

    public Result() {
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
