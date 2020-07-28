package com.mhd.basekit.viewkit.util.route.TransferInfor;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;

/**
 * @author zhangming
 * @Date 2019/7/1 13:27
 * @Description: 登录后要处理的信息
 */
public class LoginInfor {
    Postcard postcard;
    InterceptorCallback callback;

    public LoginInfor(Postcard postcard, InterceptorCallback callback) {
        this.postcard = postcard;
        this.callback = callback;
    }

    public Postcard getPostcard() {
        return postcard;
    }

    public InterceptorCallback getCallback() {
        return callback;
    }
}
