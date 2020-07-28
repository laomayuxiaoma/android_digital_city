package com.example.muheda.loadandshare;

import android.app.Activity;

import com.example.muheda.loadandshare.auth.WxAuth;
import com.example.muheda.loadandshare.model.AuthType;

/**
 * Created by 13660 on 2018/11/6.
 */

public class AuthApi {
    protected Activity mActivity;
    private static OnAutoListener mOnAutoListener;

    public AuthApi(Activity mActivity) {
        this.mActivity = mActivity;
    }

    protected void doAuth() {

    }

    public static AuthApi doAuthVerify(Activity act, AuthType type, OnAutoListener mOnAutoListener) {
        AuthApi authApi = null;
        switch (type) {
            case WXLOAD:
                authApi = new WxAuth(act);
                break;
        }
        authApi.setmOnAutoListener(mOnAutoListener);
        authApi.doAuth();

        return authApi;
    }

    public static void setmOnAutoListener(OnAutoListener mOnAutoListener) {
        AuthApi.mOnAutoListener = mOnAutoListener;
    }

    public static void setOnCompleteCallBack(String code) {
        if (mOnAutoListener != null) {
            mOnAutoListener.onComplete(code);
        }

    }

    public static void setOnErrorCallBack(String error) {
        if (mOnAutoListener != null) {
            mOnAutoListener.onError(error);
        }
    }

    public static void setOnCancelCallBack() {
        mOnAutoListener.onCancel();
    }

    public interface OnAutoListener {

        void onComplete(String code);

        void onError(String error);

        void onCancel();
    }

}
