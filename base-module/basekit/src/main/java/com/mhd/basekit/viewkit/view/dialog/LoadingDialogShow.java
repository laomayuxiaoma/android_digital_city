package com.mhd.basekit.viewkit.view.dialog;

import android.app.Activity;
import android.content.Context;

import Tool.DialogUtils;

public class LoadingDialogShow implements BaseDialogShow {

    private String hint;

    @Override
    public void show(Context mContext) {
        DialogUtils.getInstance().showProgressDialog((Activity) mContext, DialogUtils.SYSDiaLogType.IosType, "加载中");
    }

    @Override
    public void show(Context mContext, String message) {
        DialogUtils.getInstance().showProgressDialog((Activity) mContext, DialogUtils.SYSDiaLogType.IosType, message);
    }

    @Override
    public boolean isShowing() {
        return false;
    }

    @Override
    public void dismiss(int type) {
        DialogUtils.dismissProgress();
    }

}
