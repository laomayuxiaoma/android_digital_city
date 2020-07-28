package com.example.muheda.functionkit.perssionkit;

import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.jakewharton.rxbinding2.view.RxView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by 13660 on 2018/8/31.
 */

public class PerssionUtil implements LifecycleObserver {
    private static Disposable disposable;
    private static RxPermissions rxPermissions;

    public static void perssionRequest(FragmentActivity activity, String[] strPerssion, final OnPermissionListener onPermissionListener) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(strPerssion).subscribe(new Consumer<Boolean>() {
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    onPermissionListener.onPremission();
                } else {
                    onPermissionListener.unPremission();
                }

            }
        });
    }

    public static void perssion(FragmentActivity activity, View view, final String permission, final OnPermissionListener onPermissionListener) {
        try {
            activity.getLifecycle().addObserver(PerssionUtil.class.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        rxPermissions = new RxPermissions(activity);
        disposable = RxView.clicks(view)
                .compose(rxPermissions.ensure(permission))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            onPermissionListener.onPremission();
                            // Toast.makeText(MainActivity.this,"已获得权限",Toast.LENGTH_SHORT).show();
                        } else {
                            // Toast.makeText(MainActivity.this,"无法获得权限",Toast.LENGTH_SHORT).show();
                            onPermissionListener.unPremission();
                        }
                    }
                });
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public static void onDestory() {
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }

    }
}
