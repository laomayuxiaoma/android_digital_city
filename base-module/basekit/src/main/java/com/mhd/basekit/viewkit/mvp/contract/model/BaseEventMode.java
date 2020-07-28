package com.mhd.basekit.viewkit.mvp.contract.model;

/**
 * eventbus model基类
 */


//测试类统一将Eventbus的model类放到libbase中
public class BaseEventMode<T> {
    private int type;
    private T data;

    public BaseEventMode() {

    }

    public BaseEventMode(int type) {
        this.type = type;
    }

    public BaseEventMode(T data) {
        this.data = data;
    }

    public BaseEventMode(int type, T data) {
        this.type = type;
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
