package com.mhd.basekit.viewkit.view.webview.util;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.muheda.customtitleview.CustomTitleView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsBridge {

    private Context context;
    public static final String ERROR = "APPERROR";

    //具体的交互类列表
    private static List<BaseDoInApp> list = new ArrayList<>();
    private static List<BaseDoInJs> jsList = new ArrayList<>();

    public JsBridge(Context context) {
        this.context = context;
    }

    public static void initBaseDoInApp(Class cla) {
        try {
            list.add((BaseDoInApp) cla.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initBaseDoInJs(Class cla) {
        try {
            jsList.add((BaseDoInJs) cla.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setJsList(Context context, CustomTitleView mTitle) {
        for (int i = 0; i < jsList.size(); i++) {
            jsList.get(i).setContext(context, mTitle);
        }
    }

    @JavascriptInterface
    public String nativeFunction(String data, CallBackFunction function) {
        try {
            JSONObject object = new JSONObject(data);
            for (int i = 0; i < list.size(); i++) {
                String str = list.get(i).doInApp(object, context, function);
                if (!ERROR.equals(str)) {
                    return str;
                }
            }
        } catch (Exception e) {
            Log.e("MHDwebview", e.getMessage() + "");
            e.printStackTrace();
        }
        return null;
    }
}
