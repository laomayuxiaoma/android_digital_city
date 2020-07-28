package com.mhd.basekit.viewkit.mvp.contract;

/**
 * Created by 13660 on 2018/11/2.
 */

public interface IMvpView {
    void showProgressDialog();
    void hideProgressDialog(int type);
    void toastMessage(String message);
}
