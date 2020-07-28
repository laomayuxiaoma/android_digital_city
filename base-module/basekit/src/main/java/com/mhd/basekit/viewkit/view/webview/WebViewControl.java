package com.mhd.basekit.viewkit.view.webview;

import android.content.Context;
import android.content.Intent;

import com.mhd.basekit.databinding.ActivityWebViewBinding;

import java.lang.ref.WeakReference;

public abstract class WebViewControl {

    private WeakReference<Context> mWeak;

    public WebViewControl(Context context) {
        this.mWeak = new WeakReference<>(context);
        init(getViewDataBinding());
    }

    public MHDWebViewActivity getActivity() {
        Context context = getContext();
        if (context != null && context instanceof MHDWebViewActivity) {
            return (MHDWebViewActivity) context;
        }
        return null;
    }

    public abstract void init(ActivityWebViewBinding mBinding);

    public void onResume() {

    }

    public Context getContext() {
        if (mWeak == null) {
            return null;
        }
        Context context = mWeak.get();
        return context;
    }

    public ActivityWebViewBinding getViewDataBinding() {
        MHDWebViewActivity activity = getActivity();
        if (activity == null) {
            return null;
        }
        return activity.getViewDataBinding();
    }

    public void onFirstLoadFinashed(){

    }

    public void setUrl(String url) {
        MHDWebViewActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.setUrl(url);
    }

    public String getUrl() {
        MHDWebViewActivity activity = getActivity();
        if (activity == null) {
            return null;
        }
        return activity.getUrl();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

}
