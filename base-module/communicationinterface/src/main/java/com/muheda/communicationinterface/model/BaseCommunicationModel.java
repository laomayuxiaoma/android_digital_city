package com.muheda.communicationinterface.model;

/**
 * @author zhangming
 * @Date 2019/6/12 15:35
 * @Description: 通信基础Model类（参数model必须要继承此类，方便统一管理type）
 */
public class BaseCommunicationModel {

    private String type;//类型或者路由地址
    protected String methodName;//只有一个方法时不需要初始化(使用默认方法时不需要初始化)


    public BaseCommunicationModel(String type) {
        this.type = type;
    }

    public BaseCommunicationModel(String methodName, String type) {
        this.methodName = methodName;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
