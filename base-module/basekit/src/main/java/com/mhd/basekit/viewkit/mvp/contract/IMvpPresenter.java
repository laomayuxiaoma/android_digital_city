package com.mhd.basekit.viewkit.mvp.contract;

import android.os.Bundle;

/**
 * Created by 13660 on 2018/11/2.
 */

public interface IMvpPresenter<T extends IMvpView> {
    void onMvpAttachView(T view, Bundle savedInstanceState);

    void onMvpStart();

    void onMvpResume();

    void onMvpPause();

    void onMvpStop();

    void onMvpDestory();
}
