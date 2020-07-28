package com.mhd.basekit.viewkit.view.webview.util;

import android.content.Context;

import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.mhd.basekit.viewkit.view.webview.MHDWebViewActivity;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

/**
 * js调用java
 */
public class BaseDoInApp {

    private WeakReference<Context> mWeak;

    public String doInApp(JSONObject data, Context context, CallBackFunction function) {
        String backStr = "";
        try {
            mWeak = new WeakReference<>(context);
            Class clas = this.getClass();
            Method method = clas.getDeclaredMethod(data.optString("method"), JSONObject.class, CallBackFunction.class);
            method.setAccessible(true);
            backStr = (String) method.invoke(this, data.opt("data"), function);
        } catch (Exception e) {
            e.printStackTrace();
            return JsBridge.ERROR;
        }
        return backStr;
    }

    protected String getJson(Object map) {
        return JsonUtil.getJson(map);
    }

    protected <T> T jsonToTarget(T target, String json) {
        return JsonUtil.jsonToTarget(target, json);
    }

    protected Context getContext() {
        if (mWeak == null) {
            return null;
        }
        return mWeak.get();
    }

    protected MHDWebViewActivity getActivity() {
        Context context = getContext();
        if (context != null && context instanceof MHDWebViewActivity) {
            return (MHDWebViewActivity) context;
        }
        return null;
    }
}
