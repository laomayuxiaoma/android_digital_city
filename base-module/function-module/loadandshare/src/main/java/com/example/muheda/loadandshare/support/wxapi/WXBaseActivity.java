package com.example.muheda.loadandshare.support.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.muheda.loadandshare.R;
import com.example.muheda.loadandshare.ShareApi;
import com.example.muheda.loadandshare.auth.WxAuth;
import com.example.muheda.loadandshare.model.LoadAndShare;
import com.example.muheda.loadandshare.share.WxShare;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by 13660 on 2018/11/6.
 */

public class WXBaseActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, LoadAndShare.getWxId(), false);
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp instanceof SendAuth.Resp) {
            //登录
            switch (baseResp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    WxAuth.setOnCompleteCallBack(((SendAuth.Resp) baseResp).code);
                    finish();
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    WxAuth.setOnCancelCallBack();
                    finish();
                    break;

                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    WxAuth.setOnErrorCallBack(baseResp.errStr);
                    finish();
                    break;
                default:
                    WxAuth.setOnErrorCallBack(baseResp.errStr);
                    finish();
                    break;
            }
        } else {
            //分享
            ShareApi.OnShareListener onShareListener = WxShare.getmShareListener();
            int result = 0;
            switch (baseResp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    result = R.string.errcode_success;
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    result = R.string.errcode_cancel;
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    result = R.string.errcode_deny;
                    break;
                case BaseResp.ErrCode.ERR_UNSUPPORT:
                    result = R.string.errcode_unsupported;
                    break;
                default:
                    break;
            }
            boolean isListenerNull = true;
            if (baseResp.errCode == baseResp.errCode && onShareListener != null) {
                isListenerNull = false;
                onShareListener.onShareOk();
            } else if (onShareListener != null) {
                isListenerNull = false;
                onShareListener.onShareFail(baseResp.errStr);
            }
            if (isListenerNull) {
                Toast.makeText(this, getString(result), Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }
}
