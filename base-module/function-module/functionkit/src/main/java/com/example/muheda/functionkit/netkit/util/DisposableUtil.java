package com.example.muheda.functionkit.netkit.util;

import com.zhouyou.http.EasyHttp;

import io.reactivex.disposables.Disposable;

/**
 * Created by 13660 on 2018/11/15.
 */

public class DisposableUtil {

    public DisposableUtil(){

    }
    //disposable 关闭
    public static void disposableCancel(Disposable...disposables){
        for (Disposable disposable:disposables){
            if (disposable!=null){
                EasyHttp.cancelSubscription(disposable);
                disposable=null;
            }
        }
    }
}
