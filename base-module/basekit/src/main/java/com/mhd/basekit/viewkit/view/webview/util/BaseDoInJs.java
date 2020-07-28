package com.mhd.basekit.viewkit.view.webview.util;

import android.content.Context;

import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.mhd.basekit.viewkit.view.webview.MHDWebViewActivity;
import com.muheda.customtitleview.CustomTitleView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

/**
 * java调用js
 */
public class BaseDoInJs {

    private WeakReference<Context> contextWeakReference;
    private WeakReference<CustomTitleView> titleWeakReference;

    public BaseDoInJs() {
        EventBus.getDefault().register(this);
    }

    public void doInJs() {
        //        String str = callHandler(JsonUtil.initMapUtil().put("method", "doInJs")
        //                .put("test", "dsfafj").commit());
        //        Log.e("TTTTTTTTTTTPP", str + "|||");
    }

    public String callHandler(Object json, CallBackFunction function) {
        String js;
        if (json instanceof String) {
            js = (String) json;
        } else {
            js = getJson(json);
        }
        if (getContext() instanceof MHDWebViewActivity) {
            return ((MHDWebViewActivity) getContext()).callHandler(js, function);
        }
        return "";
    }

    public void setContext(Context context, CustomTitleView mTitle) {
        contextWeakReference = new WeakReference<>(context);
        titleWeakReference = new WeakReference<>(mTitle);
    }

    protected Context getContext() {
        return contextWeakReference.get();
    }

    protected CustomTitleView getTitleView() {
        return titleWeakReference.get();
    }

    protected String getJson(Object src) {
        return JsonUtil.getJson(src);
    }

    protected <T> T jsonToTarget(T target, String json) {
        return JsonUtil.jsonToTarget(target, json);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(WebDto webDto) {
        Class cla = this.getClass();
        try {
            Method declaredMethod = cla.getDeclaredMethod(webDto.getMethod(), Object.class);
            declaredMethod.invoke(this, webDto.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
