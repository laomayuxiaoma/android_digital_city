package com.example.muheda.functionkit.netstatekit;

import android.content.Context;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by 13660 on 2018/8/31.
 */

public class NetStateUtil implements LifecycleObserver {
    private static Disposable disposable;
    //监听网络状态
    public static void netState(final Context context) {
        try {
            ((FragmentActivity)context).getLifecycle().addObserver(NetStateUtil.class.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        disposable= ReactiveNetwork.observeNetworkConnectivity(context).subscribe(new Consumer<Connectivity>() {
            @Override
            public void accept(Connectivity connectivity) throws Exception {
                if (connectivity.getState() == NetworkInfo.State.CONNECTED) {
                    Toast.makeText(context,"检测到网络",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context,"检测断开",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public static void onDestory(){
        Log.d("tag","----------jiancedao -destory-");
        if (disposable!=null){
            disposable.dispose();
            disposable=null;
        }

    }
}
