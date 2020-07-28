package com.mhd.basekit.viewkit.util.net;

import com.example.muheda.functionkit.netkit.http.MHDHttp;
import com.example.muheda.functionkit.netkit.http.OnNewListener;
import com.mhd.basekit.model.net.BaseRequestParams;

import java.util.LinkedHashMap;

import io.reactivex.disposables.Disposable;

/**
 * Created by 13660 on 2018/10/19.
 */

public class MDHttp {

    /**
     * @param enumR      操作实体 包含参数校验实体 数据实体 等
     * @param httpParams 请求体
     * @param callBack   回调
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R extends Enum> Disposable post(final R enumR, final LinkedHashMap httpParams, final OnNewListener<T> callBack) {

        BaseRequestParams<LinkedHashMap> params = new BaseRequestParams();
        params.setData(httpParams);
        params.sha();
        return MHDHttp.postJson(enumR, params, callBack);
    }

    /**
     * @param enumR      操作实体 包含参数校验实体 数据实体 等
     * @param httpParams 请求体
     * @param callBack   回调
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R extends Enum> Disposable postObj(final R enumR, final Object httpParams, final OnNewListener<T> callBack) {

        return MHDHttp.postJson(enumR, httpParams, callBack);
    }
}
