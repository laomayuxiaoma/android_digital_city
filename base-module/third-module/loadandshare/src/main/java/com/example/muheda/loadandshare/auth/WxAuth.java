package com.example.muheda.loadandshare.auth;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.muheda.loadandshare.AuthApi;
import com.example.muheda.loadandshare.model.LoadAndShare;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by 13660 on 2018/11/6.
 */

public class WxAuth extends AuthApi {

    public WxAuth(Activity mActivity) {
        super(mActivity);
    }

    @Override
    protected void doAuth() {
        super.doAuth();
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat";

        IWXAPI api = WXAPIFactory.createWXAPI(mActivity, LoadAndShare.getWxId(), true);
        api.registerApp(LoadAndShare.getWxId());

        api.sendReq(req);
    }


}
