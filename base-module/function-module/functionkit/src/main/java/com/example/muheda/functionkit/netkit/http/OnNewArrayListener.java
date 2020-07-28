package com.example.muheda.functionkit.netkit.http;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * @author wangfei
 * @date 2019/9/24.
 */
public abstract class OnNewArrayListener<T>  implements OnNewListener<T>{

    @Override
    public void onSuccess(T t) throws JSONException {

    }

    public abstract void onSuccess(ArrayList<T> list) throws JSONException;
}
