package com.mhd.basekit.viewkit.util.alipay;

import android.app.Activity;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.muheda.functionkit.netkit.http.MHDHttp;
import com.example.muheda.functionkit.netkit.http.OnNewListener;
import com.example.muheda.functionkit.netkit.params.BaseParams;
import com.example.muheda.functionkit.netkit.params.HttpNewParams;
import com.example.muheda.functionkit.netkit.params.HttpParamsUtil;
import com.mhd.basekit.model.net.NengYuanResultDto;
import com.muheda.mhdsystemkit.sytemUtil.uiutil.ToastUtils;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author wangfei
 * @date 2019/5/21.
 */
public class AliPayUtils {
    private static String SUCCESS = "9000";
    private static String mUrl = "app/charge/start";
//    private static String mUrl = "https://clientxnytemporary.muheda.com/operatorPlatform/app/charge/start?pileId=398010902_C1800616&connectorId=398010902_C18006161&chargeType=1&chargeMoney=0.5&body=测试&appUnique=aaaabbbbcccddd&version=1.0&network=4G&source=Android&token=44a39f013ef7dc169f5d88bb2d07ec5c";
    private static PayTask aliPay;
    private static Object[][] mParams;
    private static IAliPayResult mListener;
    private static Disposable aliPayDisposable;

    public static void getPay(Activity activity, Object[][] params, IAliPayResult listener) {
//        mUrl = url;
        mParams = params;
        mListener = listener;

        getAliPayOrderInfo();
        aliPay = new PayTask(activity);
    }

    private static Observer observer = new Observer() {

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Object o) {
            PayResult payResult = new PayResult((Map<String, String>) o);
            String resultInfo = payResult.getResult();// 同步返回需要验证的信息
            if (SUCCESS.equals(payResult.getResultStatus())) {
                if (mListener != null) mListener.success();
            } else {
                if (mListener != null) mListener.fail();
                ToastUtils.showShort(payResult.getMemo());
            }
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };

    /**
     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
     */
    private static void getAliPayOrderInfo() {
        //测试沙箱模式
//        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);


        HttpNewParams httpNewParams = HttpParamsUtil.getCommonRequestParams(mParams);
        aliPayDisposable = MHDHttp.post(Dispose.ALI_PAY, httpNewParams, new OnNewListener<AliPayDto>() {
            @Override
            public void onSuccess(final AliPayDto aliPayDto) {
                if (mListener != null) mListener.getPayData(aliPayDto);
                final Observable observable = Observable.create(new ObservableOnSubscribe() {
                    @Override
                    public void subscribe(ObservableEmitter e) {
                        Map<String, String> result = aliPay.payV2(aliPayDto.getData().getPayInfo(), true);
                        e.onNext(result);
                        e.onComplete();
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());

                observable.subscribe(observer);
            }

            @Override
            public void onErrorOrExpection() {
            }

            @Override
            public void onOtherState(String code, String message) {
                ToastUtils.showShort(message);
            }

            @Override
            public void onNull(String code, String message) {
                ToastUtils.showShort(message);
            }
        });
    }

    private enum Dispose {
        ALI_PAY(mUrl, AliPayDto.class);

        String url;//域名
        Class modelClass; //实体Class
        BaseParams baseParams;//参数校验父类
        boolean isAuto = true;//是否自动解析json 默认是
        //    Class mRules = GunYunOriginalResultDto.class;//请求结果拦截
        Class mRules = NengYuanResultDto.class;//请求结果拦截
        Object requestJsonParams = null;//请求结果拦截

        Dispose(String url, Class modelClass) {
            this.url = url;
            this.modelClass = modelClass;
        }
    }
}
