package com.example.muheda.functionkit.netkit.model;

import com.example.muheda.functionkit.netkit.http.OnNewListener;

/**
 * @author zhangming
 * @Date 2019/8/30
 * @Description: 包含接口返回的原始字符串等
 */
public class OriginalResultDto<T> {

    public static final int NET_SUCCESS = 1;
    public static final int NET_NULL = 2;
    public static final int NET_OTHER = 3;

    private String response;
    private String code;
    private String message;
    private OnNewListener<T> callBack;
    private Class mClass;

    public OriginalResultDto(String response,Class mClass, String code, String message, OnNewListener<T> callBack) {
        this.response = response;
        this.code = code;
        this.message = message;
        this.callBack = callBack;
        this.mClass = mClass;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OnNewListener<T> getCallBack() {
        return callBack;
    }

    public void setCallBack(OnNewListener<T> callBack) {
        this.callBack = callBack;
    }

    public Class getmClass() {
        return mClass;
    }

    public void setmClass(Class mClass) {
        this.mClass = mClass;
    }
}
