package com.mhd.basekit.viewkit.util.route.service;

import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * Created by wangfei on 2019/4/12.
 */
public interface IShareService extends IProvider {
    void toShare(Context context,String getDataUrl);
    void toShare(Context context,String url, String title, String content);
    void toShare(Context context,boolean isShowWB,String url, String title, String content);
    void toShare(Context context,String url, String title, String content,IWBShareResult iwbShareResult);
    void setWBListener(Intent intent,IWBShareResult iwbShareResult);
    void dismiss();
}
