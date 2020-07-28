package com.example.muheda.functionkit.netkit.http;

import org.json.JSONException;

/**
 * Created by 13660 on 2018/10/19.
 */

public interface OnNewListener<T> {
    void onSuccess(T t) throws JSONException;
    void onErrorOrExpection();
    void onOtherState(String code,String message);
    void onNull(String code, String message);
}
