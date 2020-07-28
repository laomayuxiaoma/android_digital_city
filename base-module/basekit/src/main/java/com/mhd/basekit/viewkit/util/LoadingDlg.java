package com.mhd.basekit.viewkit.util;

import android.app.Activity;

import Tool.DialogUtils;

/**
 * @author zhangming
 * @Date 2019/7/10 13:18
 * @Description: 加载弹框
 */
public class LoadingDlg {

    public static void show(Activity activity){
        DialogUtils.getInstance().showProgressDialog(activity, DialogUtils.SYSDiaLogType.IosType);
    }

    public static void dismiss(){
        DialogUtils.getInstance().dismissProgress();
    }
}
