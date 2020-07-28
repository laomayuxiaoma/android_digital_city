package com.muheda.communicationinterface.communication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.muheda.communicationinterface.iinterface.ICommunication;
import com.muheda.communicationinterface.model.BaseCommunicationModel;
import com.muheda.communicationinterface.model.ParamCommunicationModel;
import com.muheda.communicationinterface.model.ReturnCommunicationModel;

import java.lang.ref.WeakReference;

/**
 * V:通信时希望返回的返回值类型
 *
 * @author zhangming
 * @Date 2019/6/12 16:04
 * @Description: 通信连接类（通信时统一创建此类）
 */
public class CommunicationService<V> {

    private WeakReference<ICommunication> communicationWeakReference;
    private String type = "";

    public <T extends BaseCommunicationModel> ReturnCommunicationModel<V> communication(T baseParams) {
        ICommunication communicationService = (ICommunication) ARouter.getInstance().build(baseParams.getType()).navigation();
        if (communicationService != null) {
            ParamCommunicationModel<T> params = new ParamCommunicationModel(baseParams.getType());
            params.setData(baseParams);
            ReturnCommunicationModel<V> returnCommunicationModel = new ReturnCommunicationModel<>();
            Object result = communicationService.iCommunication(params);
            if (result != null) {
                returnCommunicationModel.setData(result);
            }
            return returnCommunicationModel;
        }
        return null;
    }

}
