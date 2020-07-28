package com.muheda.mdsearchview.model;

import com.muheda.mdsearchview.icallback.ICallBack;


/**
 * @author wangfei
 * @date 2019/7/31.
 */
public abstract class SearchModelDto {

    private ICallBack callBack;

    public ICallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(ICallBack callBack) {
        this.callBack = callBack;
    }
}
