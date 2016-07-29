package com.zxl.testrxbus;

/**
 * @类说明：
 * @author：zxl
 * @CreateTime 2016/7/28.
 */
public class RxBusEvent<T> {

    private T data;
    private String message;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
