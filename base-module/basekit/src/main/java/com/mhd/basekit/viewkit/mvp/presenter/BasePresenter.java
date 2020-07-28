package com.mhd.basekit.viewkit.mvp.presenter;

import android.os.Bundle;

import com.mhd.basekit.viewkit.mvp.contract.IMvpPresenter;
import com.mhd.basekit.viewkit.mvp.contract.IMvpView;

import java.lang.ref.WeakReference;

/**
 * Created by 13660 on 2018/11/2.
 */

public class BasePresenter<T extends IMvpView> implements IMvpPresenter<T> {

    private WeakReference<T> viewRef;

    protected T getView() {
        return viewRef.get();
    }

    @Override
    public void onMvpAttachView(T view, Bundle savedInstanceState) {
        viewRef = new WeakReference<T>(view);
    }

    @Override
    public void onMvpStart() {

    }

    @Override
    public void onMvpResume() {

    }

    @Override
    public void onMvpPause() {

    }

    @Override
    public void onMvpStop() {

    }

    @Override
    public void onMvpDestory() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }
}
