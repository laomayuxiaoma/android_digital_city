package com.mhd.basekit.viewkit.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import androidx.annotation.RequiresPermission;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.muheda.functionkit.netkit.http.NetInterceptor;
import com.example.muheda.functionkit.netkit.util.IntenetUtil;
import com.example.muheda.loadandshare.model.LoadAndShare;
import com.mhd.basekit.BuildConfig;
import com.mhd.basekit.viewkit.util.wholeCommon.UrlConstant;
import com.mhd.basekit.viewkit.view.refreshView.MHDFooter;
import com.mhd.basekit.viewkit.view.refreshView.MHDHeader;
import com.muheda.customrefreshlayout.MRefreshLayout;
import com.muheda.mhdsystemkit.sytemUtil.SystemKit;
import com.muheda.mhdsystemkit.sytemUtil.functionutil.MMKVUtil;
import com.muheda.mhdsystemkit.sytemUtil.uiutil.SpannableStringUtils;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.model.HttpParams;

import static android.Manifest.permission.READ_PHONE_STATE;
import static com.mhd.basekit.viewkit.util.IgnoreTheCertificate.ignoreTheCertificate;

/**
 * @author zhangming
 * @Date 2019/4/3 15:00
 * @Description: basekit中需要初始化的地方
 */
public class InitBasekit {
    public static void init(Context mContext){
        EasyHttp.init((Application) mContext);
        SystemKit.init((Application) mContext);
        SpannableStringUtils.init((Application) mContext);
        MMKVUtil.init(mContext);
        LoadAndShare.init(mContext);

        initRefresh();
        initNewMuheda(mContext);
        initRouter((Application) mContext);
    }

    public static void initNewMuheda(Context mContext) {

        try {
            //忽略证书
            EasyHttp.getInstance().getOkHttpClientBuilder()
                    .sslSocketFactory(ignoreTheCertificate())
                    .hostnameVerifier(IgnoreTheCertificate.DO_NOT_VERIFY).followRedirects(false).followSslRedirects(false).addInterceptor(new NetInterceptor());
            HttpParams httpParams=new HttpParams();
            httpParams.put("version",getCurrentVersion(mContext));
            httpParams.put("source", "Android");
            EasyHttp.getInstance()
//                    .setBaseUrl("http://172.19.2.176:6085/operatorPlatform/") //域名
                    .setBaseUrl(UrlConstant.HTTP_URL) //域名
                    .debug("EasyHttp", true)
                    .setRetryCount(3)//网络不好自动重连3次
                    .setRetryDelay(0)
                    .addCommonParams(httpParams)
            ;//超时不重连

            /*.addCommonParams(params)*/
            //.setCertificates(this.getAssets().open("muheda.pem"));

//            HttpHeaders.setUserAgent(userAgent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("MissingPermission")
    @RequiresPermission(READ_PHONE_STATE)
    private static String getDeviceId() {
        TelephonyManager telephonyManager = (TelephonyManager) EasyHttp.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //noinspection ConstantConditions
            String iMei = telephonyManager.getImei();
            if (!TextUtils.isEmpty(iMei)) return iMei;
            String meid = telephonyManager.getMeid();
            return TextUtils.isEmpty(meid) ? "" : meid;

        }
        //noinspection ConstantConditions
        return telephonyManager.getDeviceId();
    }

    public static String getCurrentVersion(Context mContext) {
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = mContext.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(
                    mContext.getPackageName(), 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static void initRefresh() {
        MRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //                layout.setPrimaryColorsId(com.muheda.customrefreshlayout.R.color.colorPrimary, com.muheda.customrefreshlayout.R.color.colorPrimaryDark);//全局设置主题颜色
                return new MHDHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        MRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new MHDFooter(context);
            }
        });
    }

    public static void initRouter(Application application) {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.printStackTrace();
        }
        ARouter.init(application);
    }
}
