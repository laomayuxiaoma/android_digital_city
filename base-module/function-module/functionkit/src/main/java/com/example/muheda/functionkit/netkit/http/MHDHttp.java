package com.example.muheda.functionkit.netkit.http;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.RequiresPermission;

import com.example.muheda.functionkit.netkit.constant.StateConstant;
import com.example.muheda.functionkit.netkit.model.ModelDto;
import com.example.muheda.functionkit.netkit.model.OriginalResultDto;
import com.example.muheda.functionkit.netkit.params.BaseParams;
import com.example.muheda.functionkit.netkit.params.HttpNewParams;
import com.example.muheda.functionkit.netkit.util.IntenetUtil;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;

import io.reactivex.disposables.Disposable;

import static android.Manifest.permission.READ_PHONE_STATE;

/**
 * Created by 13660 on 2018/10/19.
 */

public class MHDHttp {
    public static String[] params = new String[]{"baseParams", "url", "modelClass", "isAuto", "mRules", "requestJsonParams"};

    /**
     * @param strBaseParams 参数判断实体
     * @param strUrl        url
     * @param strClass      Model Class
     * @param strboolean    是否zi动解析Json true自动解
     *                      反射根据字段字符串 获取变量值
     */
    public static void setParamsArray(String strBaseParams, String strUrl, String strClass, String strboolean, String rules, String requestJsonParams) {
        params[0] = strBaseParams;
        params[1] = strUrl;
        params[2] = strClass;
        params[3] = strboolean;
        params[4] = rules;
        params[5] = requestJsonParams;
    }


    /**
     * @param enumR      操作实体 包含参数校验实体 数据实体 等
     * @param httpParams 请求体
     * @param callBack   回调
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R extends Enum> Disposable post(final R enumR, final HttpNewParams httpParams, final OnNewListener<T> callBack) {

        initParams(httpParams);
        try {
            if (getObject(enumR, params[0]) != null) {
                if (!(((BaseParams) getObject(enumR, params[0])).getHttpNewParams(httpParams))) {
                    return null;
                }
            }
            return method("post", (String) getObject(enumR, params[1]), httpParams, getObject(enumR, params[5]), callBack, new SimpleCallBack<String>() {
                @Override
                public void onError(ApiException e) {
                    callBack.onErrorOrExpection();
                }

                @Override
                public void onSuccess(String string) {
                    try {
                        Class mClass = null;
                        if (getObject(enumR, params[2]) != null) {
                            mClass = (Class) getObject(enumR, params[2]);
                        }
                        getModel(string, mClass, (boolean) getObject(enumR, params[3]), callBack, enumR);
                    } catch (Exception e) {
                        e.printStackTrace();
                        callBack.onErrorOrExpection();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param enumR      操作实体 包含参数校验实体 数据实体 等
     * @param httpParams 请求体
     * @param callBack   回调
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R extends Enum> Disposable postJson(final R enumR, final Object httpParams, final OnNewListener<T> callBack) {


        try {
//            if (getObject(enumR, params[0]) != null) {
//                if (!(((BaseParams) getObject(enumR, params[0])).getHttpNewParams(httpParams))) {
//                    return null;
//                }
//            }
            return method("post", (String) getObject(enumR, params[1]), null, httpParams, callBack, new SimpleCallBack<String>() {
                @Override
                public void onError(ApiException e) {
                    callBack.onErrorOrExpection();
                }

                @Override
                public void onSuccess(String string) {
                    try {
                        Class mClass = null;
                        if (getObject(enumR, params[2]) != null) {
                            mClass = (Class) getObject(enumR, params[2]);
                        }
                        getModel(string, mClass, (boolean) getObject(enumR, params[3]), callBack, enumR);
                    } catch (Exception e) {
                        e.printStackTrace();
                        callBack.onErrorOrExpection();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T, R extends Enum> Disposable get(final R enumR, final HttpNewParams httpParams, final OnNewListener<T> callBack) {


        try {
            if (getObject(enumR, params[0]) != null) {
                if (!(((BaseParams) getObject(enumR, params[0])).getHttpNewParams(httpParams))) {
                    return null;
                }
            }
            return method("get", (String) getObject(enumR, params[1]), httpParams, null, callBack, new SimpleCallBack<String>() {
                @Override
                public void onError(ApiException e) {
                    callBack.onErrorOrExpection();
                }

                @Override
                public void onSuccess(String string) {
                    try {
                        Class mClass = null;
                        if (getObject(enumR, params[2]) != null) {
                            mClass = (Class) getObject(enumR, params[2]);
                        }
                        getModel(string, mClass, (boolean) getObject(enumR, params[3]), callBack, enumR);
                    } catch (Exception e) {
                        e.printStackTrace();

                        callBack.onErrorOrExpection();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取变量值
     *
     * @param enumR     枚举类
     * @param strParams 变量key
     * @param <R>
     * @return
     * @throws Exception
     */
    public static <R extends Enum> Object getObject(final R enumR, String strParams) throws Exception {
        Class enumClass = enumR.getClass();
        Field field = enumClass.getDeclaredField(strParams);
        field.setAccessible(true);
        return field.get(enumR);
    }

    /**
     * 网络请求方式
     *
     * @param method         post get
     * @param url            url
     * @param httpParams     请求实体
     * @param callBack
     * @param simpleCallback
     * @param <T>
     * @return
     */
    public static <T> Disposable method(String method, String url, final HttpNewParams httpParams, Object o, final OnNewListener<T> callBack, SimpleCallBack<String> simpleCallback) {
        Disposable disposable = null;
        switch (method) {
            case "post":
                if (null == o) {
                    disposable = HttpUtil.post(url, httpParams, simpleCallback);
                } else {
                    disposable = HttpUtil.post(url, o, simpleCallback);
                }
                break;
            case "get":
                disposable = HttpUtil.get(url, httpParams, simpleCallback);
                break;
        }
        return disposable;
    }

    /**
     * 判断model 处理方式
     *
     * @param response
     * @param aClass
     * @param is
     * @param callBack
     * @param <T>
     * @throws JSONException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T, R extends Enum> void getModel(String response, Class aClass, boolean is, final OnNewListener<T> callBack, R enumR) throws JSONException, IllegalAccessException, InstantiationException {
        JSONObject jsonObject = new JSONObject(response);
        //jsonObject.optString("data").equals("[]");
        String code = jsonObject.optString("code");
        Object data = jsonObject.opt("data");
        //若 code或data不全有 创建class继承ModelDto解析数据 则OnSuccess之外失效
        String resultCode = jsonObject.optString("resultCode");
        String message = jsonObject.optString("message");

        try {
            Class rulesClass = (Class) getObject(enumR, params[4]);
            if (rulesClass != null) {
                ProcessingRules rule = (ProcessingRules) rulesClass.newInstance();
                OriginalResultDto<T> resultDto = new OriginalResultDto(response, aClass, code, message, callBack);
                int requestType = rule.processingRules(resultDto);
                if (requestType == OriginalResultDto.NET_SUCCESS) {
                    ModelDto modelDto = (ModelDto) aClass.newInstance();
                    if (modelDto instanceof ModelDto) {
                        callBack.onSuccess((T) modelDto.toJson(resultDto.getResponse(), is));
                    } else {

                    }
                    return;
                } else if (requestType == OriginalResultDto.NET_NULL) {
                    callBack.onNull(resultDto.getCode(), resultDto.getMessage());
                    return;
                } else {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!TextUtils.isEmpty(resultCode) && TextUtils.isEmpty(message) && data == null) {
            callBack.onSuccess((T) response);
            return;
        }

        //临时购物车和立即购买

//        if (TextUtils.isEmpty(code) || data == null) {
//            ModelDto modelDto = (ModelDto) aClass.newInstance();
//            callBack.onSuccess((T) modelDto.toJson(response, is));
//            return;
//        }

        if (StateConstant.REQUEST_CODE_200.equals(jsonObject.getString("code"))) {
            if (aClass == null) {
                callBack.onSuccess((T) response);
                return;
            }

            int dataInt = jsonObject.optInt("data", Integer.MIN_VALUE);
            if (dataInt != Integer.MIN_VALUE) {
                ModelDto modelDto = (ModelDto) aClass.newInstance();
                callBack.onSuccess((T) modelDto.toJson(response, is));
                return;
            }

            if (jsonObject.optString("data").length() < StateConstant.REQUEST_LENG_5) {
                callBack.onNull(jsonObject.getString("code"), jsonObject.getString("message"));
                return;
            }
            ModelDto modelDto = (ModelDto) aClass.newInstance();
            callBack.onSuccess((T) modelDto.toJson(response, is));
            return;
        }
        callBack.onOtherState(jsonObject.getString("code"), jsonObject.getString("message"));
    }


    private static void initParams(HttpNewParams mParams) {
        mParams.put("appUnique", getDeviceId());
        mParams.put("network", IntenetUtil.getNetworkState(EasyHttp.getContext()));
        Log.d("---params--", mParams.toString());
    }

    private static boolean permission;

    @SuppressLint("MissingPermission")
    @RequiresPermission(READ_PHONE_STATE)
    public static String getDeviceId() {
        if (!permission) {
            PackageManager pm = EasyHttp.getContext().getPackageManager();
            permission = (PackageManager.PERMISSION_GRANTED ==
                    pm.checkPermission(Manifest.permission.READ_PHONE_STATE, getCurrentVersion(EasyHttp.getContext()).packageName));
        }
        if (permission && Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
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
        } else {
            String m_szDevIDShort = "35" +
                    Build.BOARD.length() % 10 +
                    Build.BRAND.length() % 10 +
                    Build.CPU_ABI.length() % 10 +
                    Build.DEVICE.length() % 10 +
                    Build.DISPLAY.length() % 10 +
                    Build.HOST.length() % 10 +
                    Build.ID.length() % 10 +
                    Build.MANUFACTURER.length() % 10 +
                    Build.MODEL.length() % 10 +
                    Build.PRODUCT.length() % 10 +
                    Build.TAGS.length() % 10 +
                    Build.TYPE.length() % 10 +
                    Build.USER.length() % 10; //13 digits
            return m_szDevIDShort;
        }


    }

    public static PackageInfo getCurrentVersion(Context mContext) {
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = mContext.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            return packageManager.getPackageInfo(
                    mContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
