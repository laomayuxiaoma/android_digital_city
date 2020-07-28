package com.mhd.basekit.viewkit.util.wxpay;



import android.content.Context;

import com.mhd.basekit.viewkit.util.Common;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WX_Pay {

    public IWXAPI api;
    private PayReq req;

    public WX_Pay(Context context) {

        api = WXAPIFactory.createWXAPI(context, Common.getWxAPPid());
        api.registerApp(Common.getWxAPPid());
    }

    /**
     * 向微信服务器发起的支付请求
     */
    public void pay(String appid,String partnerid,String prepayid,String nonceStr,String timeStamp,String sign,String pack) {

        req = new PayReq();

        req.appId = appid;//APPID
        req.partnerId = partnerid;//    商户号
        req.prepayId = prepayid;//  预付款ID
        req.nonceStr = nonceStr;//随机数
        req.timeStamp = timeStamp;//时间戳
        req.packageValue = pack;//固定值Sign=WXPay


        req.sign = sign;//签名


        api.sendReq(req);
    }




}
