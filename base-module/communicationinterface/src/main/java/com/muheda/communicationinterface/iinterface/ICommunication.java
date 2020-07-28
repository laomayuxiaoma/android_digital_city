package com.muheda.communicationinterface.iinterface;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.muheda.communicationinterface.model.ParamCommunicationModel;
/**
 * @author zhangming
 * @Date 2019/6/12 15:57
 * @Description: 基础通信接口（通信实现类统一实现此接口）
 */
public interface ICommunication<T> extends IProvider {

    Object iCommunication(ParamCommunicationModel<T> params);

}
