package com.example.muheda.loadandshare;

import android.app.Activity;

import com.example.muheda.loadandshare.model.ShareContent;
import com.example.muheda.loadandshare.model.ShareType;
import com.example.muheda.loadandshare.share.WxFriendShare;
import com.example.muheda.loadandshare.share.WxShare;

import java.lang.ref.WeakReference;

/**
 * Created by 13660 on 2018/11/7.
 */

public class ShareApi {
    protected Activity mActivity;
    protected ShareContent mShareContent;
    private static WeakReference<OnShareListener> mWeakShareListener;

    public ShareApi(Activity mActivity) {
        this.mActivity = mActivity;
    }

    protected void doshare() {

    }

    public static ShareApi doShareVeify(Activity act, ShareType type, ShareContent shareContent, OnShareListener onShareListener) {
        ShareApi shareApi = null;
        switch (type) {
            case WXSHARE:
                shareApi = new WxShare(act);
                break;
            case WXFRIENDSHARE:
                shareApi = new WxFriendShare(act);
                break;
        }

        shareApi.setmShareContent(shareContent);
        shareApi.setmShareListener(onShareListener);
        shareApi.doshare();
        return shareApi;
    }

    public ShareContent getmShareContent() {
        return mShareContent;
    }

    public void setmShareContent(ShareContent mShareContent) {
        this.mShareContent = mShareContent;
    }

    /**
     * 分享回调
     */
    public interface OnShareListener {

        /**
         * 分享回调-成功分享
         */
        void onShareOk();

        /**
         * 分享回调-支付分享
         */
        void onShareFail(String errString);
    }

    public static OnShareListener getmShareListener() {
        if (mWeakShareListener == null) {
            return null;
        }
        OnShareListener onShareListener = mWeakShareListener.get();
        mWeakShareListener = null;
        return onShareListener;
    }

    public void setmShareListener(OnShareListener mShareListener) {
        ShareApi.mWeakShareListener = new WeakReference<>(mShareListener);
    }
}
