package com.mhd.basekit.viewkit.view.dialog;

import android.content.Context;

//有关dlog显示隐藏的接口
public interface BaseDialogShow extends DialogMessage {

    void show(Context mContext);

    boolean isShowing();

    void dismiss(int type);
}
