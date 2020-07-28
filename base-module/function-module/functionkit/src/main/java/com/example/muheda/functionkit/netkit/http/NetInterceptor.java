package com.example.muheda.functionkit.netkit.http;

import com.muheda.mhdsystemkit.sytemUtil.SystemKit;
import com.muheda.mhdsystemkit.sytemUtil.functionutil.NetWorkUtils;
import com.muheda.mhdsystemkit.sytemUtil.uiutil.ToastUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author wangfei
 * @date 2019/12/3.
 */
public class NetInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetWorkUtils.isNetworkConnected(SystemKit.getContext())) {
            ToastUtils.showShort("未检测到可用网络");
        }
        return chain.proceed(chain.request());
    }
}
