package com.muheda.communicationinterface.model;

/**
 * @author zhangming
 * @Date 2019/6/12 15:47
 * @Description: 参数model类
 */
public class ParamCommunicationModel<T> extends BaseCommunicationModel{

    private T data;

    public ParamCommunicationModel(String type) {
        super(type);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
