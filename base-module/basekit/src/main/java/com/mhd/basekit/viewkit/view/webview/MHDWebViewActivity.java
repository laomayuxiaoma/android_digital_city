package com.mhd.basekit.viewkit.view.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.mhd.basekit.R;
import com.mhd.basekit.databinding.ActivityWebViewBinding;
import com.mhd.basekit.viewkit.util.route.service.IWBShareResult;
import com.mhd.basekit.viewkit.view.BaseDBActivity;
import com.mhd.basekit.viewkit.view.webview.util.JsBridge;
import com.mhd.basekit.viewkit.view.webview.util.WebDto;
import com.muheda.customtitleview.CustomTitleView;
import com.muheda.mhdsystemkit.systemUI.commonView.MHDWebview;
import com.muheda.mhdsystemkit.sytemUtil.uiutil.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class MHDWebViewActivity extends BaseDBActivity<ActivityWebViewBinding> implements MHDWebview.OnChangeRequestedOrientation,
        IWBShareResult, Runnable, BridgeHandler, MHDWebview.OnLoadUrl {

    public static final String WEBVIWCONTROL = "MWEBVIWCONTROL";
    public static final String JSBACK = "jsBack";//返回

    private ProgressBar web_progress;
    private MHDWebview mhdWebview;
    private JsBridge jsBridge;
    private View top;
    //需在启动activity之前设置
    private Class mWebViewControl;
    private WebViewControl webViewControlInstance;
    private String mWebViewControlName;
    private String title = "";
    private String url = "";
    private boolean hideTitle = false;
    private boolean isTitleFromNet = false;//title是否来自网页
    private boolean isShowLeftBack = true;
    private boolean isShowRightText = false;
    private boolean isShowTitleBottom = true;

    private ArrayList<String> urls = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
        }
        urls.clear();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String url = intent.getStringExtra("url");
        if (!TextUtils.isEmpty(url)) {
            mBinding.webcommonWebview.loadUrl(url);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        jsBridge.setJsList(this, base_title);
        if (webViewControlInstance != null) {
            webViewControlInstance.onResume();
        }
    }

    @Override
    protected int getLayoutDBId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initDBView() {
        initValue();
        web_progress = (ProgressBar) findViewById(R.id.webcommon_progress);
        mhdWebview = mBinding.webcommonWebview;
        mhdWebview.setmOnChangeRequestedOrientation(this);
        top = findViewById(R.id.top);

        web_progress.setProgressDrawable(this.getResources().getDrawable(R.drawable.progress_bar));
        ((TextView) base_title.getRootView().findViewById(R.id.container_title)).setMaxWidth(getWidth());
        initWebView();
        setmWebViewControl();
        initData();
    }

    private void initValue() {
        mWebViewControlName = getIntent().getStringExtra(WEBVIWCONTROL);
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        hideTitle = getIntent().getBooleanExtra("hideTitle", false);
        isTitleFromNet = getIntent().getBooleanExtra("isTitleFromNet", false);
        isShowTitleBottom = getIntent().getBooleanExtra("isShowTitleBottom", true);
    }

    private void initWebView() {
        mhdWebview.setmOnLoadUrl(this);
        mhdWebview.setWebChromeClient(new CustomWebChromeClient());
        mhdWebview.setOnTitleListener(new MHDWebview.OnTitleListener() {
            @Override
            public void onReceivedTitle(String s) {
                if (!TextUtils.isEmpty(s) && isTitleFromNet) {
                    setTitle(s);
                }
            }
        });


        //js交互
        jsBridge = new JsBridge(this);
        //        mhdWebview.addJavascriptInterface(jsBridge, "test");
        mhdWebview.registerHandler("nativeFunction", this);
        mhdWebview.setDefaultHandler(this);

    }

    @Override
    public void handler(String data, CallBackFunction function) {
        jsBridge.nativeFunction(data, function);
    }

    private int getWidth() {
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels / 16 * 11;
    }

    private void initData() {
        if (null == title) {
            title = "";
        }
        setTitle(title);
        base_title.setVisibility(hideTitle ? View.GONE : View.VISIBLE);
        top.setVisibility(hideTitle ? View.GONE : View.VISIBLE);
        base_title.enableBottomLineShow(isShowTitleBottom);

        mhdWebview.loadUrl(url);
        mhdWebview.post(this);
        //                BaseDoInJs js = new BaseDoInJs(this);
        //                js.doInJs();
    }

    @Override
    protected void initDB() {
        this.base_title.setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageGoBack();
            }
        });
    }

    public String callHandler(String jsS, CallBackFunction function) {
        mhdWebview.callHandler("jsFunction", jsS, function);
        return "";
    }

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    private void setmWebViewControl() {
        if (TextUtils.isEmpty(mWebViewControlName)) {
            return;
        }
        try {
            mWebViewControl = Class.forName(mWebViewControlName);
            Constructor declaredConstructor = mWebViewControl.getDeclaredConstructor(Context.class);
            declaredConstructor.setAccessible(true);
            webViewControlInstance = (WebViewControl) declaredConstructor.newInstance(this);
            mBinding.webcommonWebview.addJavascriptInterface(webViewControlInstance, "jsInterface");
        } catch (Exception e) {
            Log.e("MHDwebview", e.getMessage() + "");
        }
    }

    public ActivityWebViewBinding getViewDataBinding() {
        return mBinding;
    }

    @Override
    protected void initMvp(Bundle savedInstanceState) {

    }

    @Override
    protected void initConfig() {

    }

    @Override
    protected void replaceDBLoad() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return pageGoBack();
    }

    public boolean pageGoBack() {
        if (urls.contains(mBinding.webcommonWebview.getUrl())) {
            EventBus.getDefault().post(new WebDto(JSBACK));
            return true;
        }
        if (mhdWebview.back(0, null)) {
            return true;
        } else if (mhdWebview.canGoBack()) {
            mhdWebview.goBack();
            return true;
        }
        finish();
        return false;
    }

    @Override
    public void onRequestedOrientationChanged(int i) {
        setRequestedOrientation(i);//设置竖屏
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void addUrl(String url) {
        if (!urls.contains(url)) {
            urls.add(url);
        }
    }

    public CustomTitleView getBaseTitle() {
        return base_title;
    }

    //分享结果 微博
    @Override
    public void succeed() {
        ToastUtils.showLong("分享成功");
    }

    @Override
    public void cancel() {
        ToastUtils.showLong("分享取消");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void error() {
        ToastUtils.showLong("分享失败");
    }

    @Override
    public void run() {
        if (webViewControlInstance != null) {
            webViewControlInstance.onFirstLoadFinashed();
        }
    }

    @Override
    public void onLoadUrl(String url) {
        if (!url.contains("javascript")) {
//            EventBus.getDefault().post(new WebDto("url"));
            if (isShowLeftBack) {
                base_title.enableLeftShow(true);
            }
            if (!isShowRightText) {
                base_title.setRightText("");
            }
        }
    }

    public void showProgressDialog() {
        showLoading();
    }

    public void hideProgressDialog(int type) {
        dismiss(type);
    }

    public class CustomWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                web_progress.setVisibility(View.INVISIBLE);
            } else {
                if (View.GONE == web_progress.getVisibility()) {
                    web_progress.setVisibility(View.VISIBLE);
                }
                web_progress.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (webViewControlInstance != null) {
            webViewControlInstance.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(WebDto webDto) {
//        if ("url".equals(webDto.getMethod())){
//            initDB();
//        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        if (mBinding.webcommonWebview != null) {
            mBinding.webcommonWebview.stopLoading();
            mBinding.webcommonWebview.clearHistory();
            mBinding.webcommonWebview.clearCache(true);
            mBinding.webcommonWebview.loadUrl("about:blank");
            //            mBinding.webcommonWebview.pauseTimers();
        }
        super.onDestroy();
    }
}
