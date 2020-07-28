package com.muheda.communicationinterface.model.test;

import com.muheda.communicationinterface.model.BaseCommunicationModel;

/**
 * @author zhangming
 * @Date 2019/6/12 17:05
 * @Description: 测试返回
 */
public class CommunicationTestModel extends BaseCommunicationModel {

    private String name;

    public CommunicationTestModel(String type, String name) {
        super(type);
        this.name = name;
    }

    public CommunicationTestModel(String type, String name, String methodName) {
        super(methodName, type);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public CommunicationTestModel setName(String name) {
        this.name = name;
        return this;
    }
}
