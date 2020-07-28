package com.example.muheda.functionkit.timestubkit;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 13660 on 2018/8/31.
 */

public class TimeStub implements LifecycleObserver {

    private static Disposable disposable;

    public static void timeStub(FragmentActivity activity, Consumer<Long> consumer){
        timeStub(activity,1l,consumer);
    }
    //延时操作
    public static void timeStub(FragmentActivity activity,Long decly, Consumer<Long> consumer){
        try {
            activity.getLifecycle().addObserver(TimeStub.class.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        disposable=Observable.timer(decly, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(consumer);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public static void onDestory(){
        if (disposable!=null){
            disposable.dispose();
            disposable=null;
        }

    }

}
