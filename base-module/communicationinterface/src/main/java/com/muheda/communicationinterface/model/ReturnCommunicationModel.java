package com.muheda.communicationinterface.model;

/**
 * @author zhangming
 * @Date 2019/6/12 15:47
 * @Description: 返回model类
 */
public class ReturnCommunicationModel<T> {
    private Object data;

    public T getData() {
        return (T) data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
