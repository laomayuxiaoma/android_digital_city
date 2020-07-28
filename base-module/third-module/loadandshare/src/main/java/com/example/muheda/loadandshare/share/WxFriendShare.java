package com.example.muheda.loadandshare.share;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.example.muheda.loadandshare.ShareApi;
import com.example.muheda.loadandshare.model.LoadAndShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by 13660 on 2018/11/7.
 */

public class WxFriendShare extends ShareApi {

    public WxFriendShare(Activity mActivity) {
        super(mActivity);
    }

    @Override
    protected void doshare() {
        super.doshare();
        IWXAPI api = WXAPIFactory.createWXAPI(mActivity, LoadAndShare.getWxId(), true);
        api.registerApp(LoadAndShare.getWxId());
        if (!api.isWXAppInstalled()) {
            Toast.makeText(mActivity, "分享失败，未安装微信", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mShareContent!=null){
        WXWebpageObject webpageObject=new WXWebpageObject();
        webpageObject.webpageUrl=mShareContent.getUrl();
        WXMediaMessage msg=new WXMediaMessage(webpageObject);
        msg.title=mShareContent.getTitle();
        msg.description=mShareContent.getDescription();
        Bitmap thumb = BitmapFactory.decodeResource(mActivity.getResources(), mShareContent.getDrawId());
        msg.setThumbImage(thumb);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "webpage" + System.currentTimeMillis();;
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;
//                    req.openId = Social.getWeixinId();
        api.sendReq(req);
        }else{
            throw new NullPointerException("shareContent is null share failed");
        }
    }
}
