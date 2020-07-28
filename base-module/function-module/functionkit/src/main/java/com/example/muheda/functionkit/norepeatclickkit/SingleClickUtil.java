package com.example.muheda.functionkit.norepeatclickkit;

import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * Created by 13660 on 2018/10/9.
 */

public class SingleClickUtil {


    /**默认5s间隔时间
     * @param view
     * @param onSingleClickListener
     */
    public static void setOnSingleClickListener(final View view,final OnSingleClickListener onSingleClickListener){
        setOnSingleClickListener(view,5,onSingleClickListener);
    }

    /**防止控件重复点击
     * @param view 控件
     * @param duration 间隔时间
     * @param onSingleClickListener 回调
     */
    public static void setOnSingleClickListener(final View view,long duration,final OnSingleClickListener onSingleClickListener){
        RxView.clicks(view).throttleFirst(duration, TimeUnit.SECONDS)
                           .subscribe(new Consumer<Object>() {
                               @Override
                               public void accept(Object o) throws Exception {
                                   onSingleClickListener.onSingleClick(view);
                               }
                           });
    }
}
