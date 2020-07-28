package com.mhd.basekit.viewkit.util.route;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.mhd.basekit.viewkit.mvp.contract.model.BaseEventMode;
import com.mhd.basekit.viewkit.util.Common;
import com.mhd.basekit.viewkit.util.eventbus.EventBusManager;
import com.mhd.basekit.viewkit.util.route.TransferInfor.LoginInfor;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

import static com.mhd.basekit.viewkit.util.wholeCommon.EventBusVariable.IS_LOGIN;

/**
 * @author zhangming
 * @Date 2019/7/1 11:19
 * @Description: 登陆拦截器
 */
@Interceptor(priority = 1)
public class LoginInterceptor implements IInterceptor {

    private String[] avoidLanding = new String[]{
            RouteConstant.DATA_LOGIN
    };

    @Override
    public void process(final Postcard postcard, final InterceptorCallback callback) {
//        Log.e("TTTTTTTTTT", postcard.getAction() + "|||" + postcard.getPath() + "||" + callback);
        callback.onContinue(postcard);
//        if (Common.getUserInfo() != null || isContainsUrl(postcard.getPath())) {
//            callback.onContinue(postcard);
//        } else {
//            RouteUtil.routeSkip(RouteConstant.DATA_LOGIN, new String[][]{});
//            Observable.timer(1, TimeUnit.SECONDS)
//                    .subscribe(new Consumer<Long>() {
//                        @Override
//                        public void accept(Long aLong) throws Exception {
//                            BaseEventMode<LoginInfor> objectBaseEventMode = new BaseEventMode<>(IS_LOGIN);
//                            objectBaseEventMode.setData(new LoginInfor(postcard, callback));
//                            EventBusManager.post(objectBaseEventMode);
//                        }
//                    });
//        }
    }

    @Override
    public void init(Context context) {

    }

    private boolean isContainsUrl(String url) {
        for (String str : avoidLanding) {
            if (str.contains(url)) {
                return true;
            }
        }
        return false;
    }
}
