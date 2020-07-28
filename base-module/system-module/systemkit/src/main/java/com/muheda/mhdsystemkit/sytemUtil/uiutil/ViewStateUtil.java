package com.muheda.mhdsystemkit.sytemUtil.uiutil;

import android.view.View;

/**
 * Created by 13660 on 2018/6/11.
 * 处理view的显示与隐藏
 */

public class ViewStateUtil {

    public static void viewVisible(View... views) {
        for (View view:views){
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void viewInVisible(View... views) {
        for (View view:views){
            view.setVisibility(View.INVISIBLE);
        }
    }

    public static void viewGone(View... views) {
        for (View view:views){
            view.setVisibility(View.GONE);
        }
    }

}
