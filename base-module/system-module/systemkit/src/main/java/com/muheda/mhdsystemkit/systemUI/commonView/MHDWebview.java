package com.muheda.mhdsystemkit.systemUI.commonView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.ClientCertRequest;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.HttpAuthHandler;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SafeBrowsingResponse;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;

import java.util.Map;
import java.util.Stack;

public class MHDWebview extends BridgeWebView {

    protected static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    private View customView;
    private FrameLayout fullscreenContainer;
    private WebChromeClient.CustomViewCallback customViewCallback;
    private OnChangeRequestedOrientation mOnChangeRequestedOrientation;

    private static String baseUrl = "";
    private boolean mIsRedirect = false;
    private boolean isHanledUrl = false;
    private boolean isCanOverrideUrlReload = false;
    private String repeatUrl;
    /**
     * 记录URL的栈
     */
    private final Stack<String> mUrls = new Stack<>();

    private OnTitleListener titleListener;
    private OnLoadUrl mOnLoadUrl;

    private static final String[] acceptableSchemes = {
            "http:",
            "https:",
            "file:",
            "javascript"
    };

    private static final String[] paySchemes = {
            "weixin:",
            "weixins:",
            "alipays:",
            "alipay:"
    };

    private static final String[] acceptableSchemesTwo = {
            "http:",
            "https:",
    };

    public MHDWebview(Context context) {
        this(context, null);
    }

    public MHDWebview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MHDWebview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //声明WebSettings子类
        WebSettings webSettings = this.getSettings();

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        webSettings.setDomStorageEnabled(true);
        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        this.setWebViewClient(new WebViewClient());
        this.setWebChromeClient(new WebChromeClient());
    }

    @Override
    public void setWebChromeClient(final WebChromeClient client) {
        WebChromeClient webChromeClient = new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                client.onProgressChanged(view, newProgress);
                super.onProgressChanged(view, newProgress) ;
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                if (null != titleListener) {
                    titleListener.onReceivedTitle(title);
                }
                client.onReceivedTitle(view, title);
                super.onReceivedTitle(view, title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                client.onReceivedIcon(view, icon);
                super.onReceivedIcon(view, icon);
            }

            @Override
            public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
                client.onReceivedTouchIconUrl(view, url, precomposed);
                super.onReceivedTouchIconUrl(view, url, precomposed);
            }

            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                showCustomView(view, callback);
                client.onShowCustomView(view, callback);
                super.onShowCustomView(view, callback);
            }


            @Override
            public void onHideCustomView() {
                hideCustomView();
                client.onHideCustomView();
                super.onHideCustomView();
            }

            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
                return client.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
            }

            @Override
            public void onRequestFocus(WebView view) {
                client.onRequestFocus(view);
                super.onRequestFocus(view);
            }

            @Override
            public void onCloseWindow(WebView window) {
                client.onCloseWindow(window);
                super.onCloseWindow(window);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return client.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return client.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                return client.onJsPrompt(view, url, message, defaultValue, result);
            }

            @Override
            public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
                return client.onJsBeforeUnload(view, url, message, result);
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                client.onGeolocationPermissionsShowPrompt(origin, callback);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }

            @Override
            public void onGeolocationPermissionsHidePrompt() {
                client.onGeolocationPermissionsHidePrompt();
                super.onGeolocationPermissionsHidePrompt();
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onPermissionRequest(PermissionRequest request) {
                client.onPermissionRequest(request);
                super.onPermissionRequest(request);
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onPermissionRequestCanceled(PermissionRequest request) {
                client.onPermissionRequestCanceled(request);
                super.onPermissionRequestCanceled(request);
            }

            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return client.onConsoleMessage(consoleMessage);
            }

            @Nullable
            @Override
            public Bitmap getDefaultVideoPoster() {
                return client.getDefaultVideoPoster();
            }

            @Nullable
            @Override
            public View getVideoLoadingProgressView() {
                FrameLayout frameLayout = new FrameLayout(getContext());
                frameLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
                return frameLayout;
            }

            @Override
            public void getVisitedHistory(ValueCallback<String[]> callback) {
                client.getVisitedHistory(callback);
                super.getVisitedHistory(callback);
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                return client.onShowFileChooser(webView, filePathCallback, fileChooserParams);
            }
        };
        super.setWebChromeClient(webChromeClient);
    }

    /**
     * 视频播放全屏
     **/
    private void showCustomView(View view, WebChromeClient.CustomViewCallback callback) {
        // if a view already exists then immediately terminate the new one
        if (customView != null) {
            callback.onCustomViewHidden();
            return;
        }

        if (!(getContext() instanceof FragmentActivity)) {
            return;
        }

        ((FragmentActivity) getContext()).getWindow().getDecorView();

        FrameLayout decor = (FrameLayout) ((FragmentActivity) getContext()).getWindow().getDecorView();
        fullscreenContainer = new FullscreenHolder(getContext());
        fullscreenContainer.addView(view, COVER_SCREEN_PARAMS);
        decor.addView(fullscreenContainer, COVER_SCREEN_PARAMS);
        if (mOnChangeRequestedOrientation != null) {
            mOnChangeRequestedOrientation.onRequestedOrientationChanged(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//设置横屏
        }
        customView = view;
        setStatusBarVisibility(false);
        customViewCallback = callback;
    }

    /**
     * 隐藏视频全屏
     */
    private void hideCustomView() {
        if (customView == null) {
            return;
        }

        if (!(getContext() instanceof FragmentActivity)) {
            return;
        }

        setStatusBarVisibility(true);
        FrameLayout decor = (FrameLayout) ((FragmentActivity) getContext()).getWindow().getDecorView();
        decor.removeView(fullscreenContainer);
        fullscreenContainer = null;
        customView = null;
        if (mOnChangeRequestedOrientation != null) {
            mOnChangeRequestedOrientation.onRequestedOrientationChanged(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置竖屏
        }
        customViewCallback.onCustomViewHidden();
        this.setVisibility(View.VISIBLE);
    }

    /**
     * 全屏容器界面
     */
    static class FullscreenHolder extends FrameLayout {

        public FullscreenHolder(Context ctx) {
            super(ctx);
            setBackgroundColor(ctx.getResources().getColor(android.R.color.black));
        }

        @Override
        public boolean onTouchEvent(MotionEvent evt) {
            return true;
        }
    }

    private void setStatusBarVisibility(boolean visible) {
        if (!(getContext() instanceof FragmentActivity)) {
            return;
        }
        int flag = visible ? 0 : WindowManager.LayoutParams.FLAG_FULLSCREEN;
        ((FragmentActivity) getContext()).getWindow().setFlags(flag, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public boolean back(int keyCode, KeyEvent event) {
        if (customView != null) {
            hideCustomView();
            return true;
        }
        return false;
    }


    public void setNativeWebViewClient(WebViewClient client) {
        super.setWebViewClient(client);
    }

    public void setNativeWebChromeClient(WebChromeClient client) {
        super.setWebChromeClient(client);
    }

    private boolean handleOverrideUrl(WebView view, String url) {
        if (TextUtils.isEmpty(url)) {
            return true;
        }
        if (isOtherUrl(url)) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                getContext().startActivity(intent);
            } catch (Exception e) {
                Log.e("MHD_webview", e.getMessage() + "");
                return true;
            }
            return true;
        }
        if (isAcceptableUrl(url) && handleRepeatUrl(url)) {
            view.loadUrl(url);
        }else if (!handleRepeatUrl(url)){
            if (view.canGoBack()) {
                view.goBack();
                return true;
            }
            if (view.getContext() instanceof Activity){
                ((Activity)view.getContext()).finish();
            }
        }
        return true;
    }

    @Override
    public void setWebViewClient(final WebViewClient client) {
        WebViewClient mClient = new BridgeWebViewClient(this) {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (isHanledUrl) {
                    isHanledUrl = false;
                    return super.shouldOverrideUrlLoading(view, url);
                }
                client.shouldOverrideUrlLoading(view, url);
                boolean back = super.shouldOverrideUrlLoading(view, url);
                if (back) {
                    return back;
                }
                return handleOverrideUrl(view, url);
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                isHanledUrl = true;
                client.shouldOverrideUrlLoading(view, request);
                boolean back = super.shouldOverrideUrlLoading(view, request.getUrl().toString());
                if (back) {
                    return back;
                }
                return handleOverrideUrl(view, request.getUrl().toString());
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                client.onPageStarted(view, url, favicon);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                client.onPageFinished(view, url);
                super.onPageFinished(view, url);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                client.onLoadResource(view, url);
                super.onLoadResource(view, url);
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onPageCommitVisible(WebView view, String url) {
                client.onPageCommitVisible(view, url);
                super.onPageCommitVisible(view, url);
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return client.shouldInterceptRequest(view, request);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                client.onReceivedError(view, errorCode, description, failingUrl);
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                client.onReceivedError(view, request, error);
                super.onReceivedError(view, request, error);
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                client.onReceivedHttpError(view, request, errorResponse);
                super.onReceivedHttpError(view, request, errorResponse);
            }

            @Override
            public void onFormResubmission(WebView view, Message dontResend, Message resend) {
                client.onFormResubmission(view, dontResend, resend);
                super.onFormResubmission(view, dontResend, resend);
            }

            @Override
            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
                client.doUpdateVisitedHistory(view, url, isReload);
                super.doUpdateVisitedHistory(view, url, isReload);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                client.onReceivedSslError(view, handler, error);
                super.onReceivedSslError(view, handler, error);
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
                client.onReceivedClientCertRequest(view, request);
                super.onReceivedClientCertRequest(view, request);
            }

            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                client.onReceivedHttpAuthRequest(view, handler, host, realm);
                super.onReceivedHttpAuthRequest(view, handler, host, realm);
            }

            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                return client.shouldOverrideKeyEvent(view, event);
            }

            @Override
            public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
                client.onUnhandledKeyEvent(view, event);
                super.onUnhandledKeyEvent(view, event);
            }

            @Override
            public void onScaleChanged(WebView view, float oldScale, float newScale) {
                client.onScaleChanged(view, oldScale, newScale);
                super.onScaleChanged(view, oldScale, newScale);
            }

            @Override
            public void onReceivedLoginRequest(WebView view, String realm, @Nullable String account, String args) {
                client.onReceivedLoginRequest(view, realm, account, args);
                super.onReceivedLoginRequest(view, realm, account, args);
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public boolean onRenderProcessGone(WebView view, RenderProcessGoneDetail detail) {
                return client.onRenderProcessGone(view, detail);
            }

            @RequiresApi(api = Build.VERSION_CODES.O_MR1)
            @Override
            public void onSafeBrowsingHit(WebView view, WebResourceRequest request, int threatType, SafeBrowsingResponse callback) {
                client.onSafeBrowsingHit(view, request, threatType, callback);
                super.onSafeBrowsingHit(view, request, threatType, callback);
            }
        };
        super.setWebViewClient(mClient);
    }

    private boolean isOtherUrl(String url) {
        for (int i = 0; i < acceptableSchemes.length; i++) {
            if (url.startsWith(acceptableSchemes[i])) {
                return false;
            }
        }

        for (int i = 0; i < paySchemes.length; i++) {
            if (url.startsWith(paySchemes[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean isAcceptableUrl(String url) {
        for (int i = 0; i < acceptableSchemes.length; i++) {
            if (url.startsWith(acceptableSchemes[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void loadUrl(String url) {
        super.loadUrl(connectUrl(url));
        if (mOnLoadUrl != null){
            mOnLoadUrl.onLoadUrl(url);
        }
    }

    @Override
    public void loadUrl(String url, Map<String, String> additionalHttpHeaders) {
        super.loadUrl(connectUrl(url), additionalHttpHeaders);
    }

    public static void setBaseUrl(String baseUrl) {
        MHDWebview.baseUrl = baseUrl;
    }

    public void setCanOverrideUrlReload(boolean canOverrideUrlReload) {
        isCanOverrideUrlReload = canOverrideUrlReload;
    }

    private boolean handleRepeatUrl(String url) {
        if (url.equalsIgnoreCase(repeatUrl) && !isCanOverrideUrlReload) {
            return false;
        }
        repeatUrl = url;
        return true;
    }

    //判断是否包含域名
    private boolean isContainsBaseUrl(String url) {
        if (url == null) {
            return false;
        }

        for (int i = 0; i < acceptableSchemes.length; i++) {
            if (url.startsWith(acceptableSchemes[i])) {
                return true;
            }
        }

        if (url.contains("://")) {//其他域名如支付宝支付
            return true;
        }

        if (TextUtils.isEmpty(baseUrl)) {
            //            throw new IllegalArgumentException("必须先调用setBaseUrl()方法初始化baseUrl");
        }
        return false;
    }

    private String connectUrl(String url) {
        if (!isContainsBaseUrl(url)) {
            return baseUrl + url;
        }
        return url;
    }

    public void setmOnChangeRequestedOrientation(OnChangeRequestedOrientation mOnChangeRequestedOrientation) {
        this.mOnChangeRequestedOrientation = mOnChangeRequestedOrientation;
    }

    public void setOnTitleListener(OnTitleListener titleListener) {
        this.titleListener = titleListener;
    }

    public void setmOnLoadUrl(OnLoadUrl mOnLoadUrl){
        this.mOnLoadUrl = mOnLoadUrl;
    }

    public interface OnTitleListener {
        void onReceivedTitle(String title);
    }

    public interface OnLoadUrl{
        void onLoadUrl(String url);
    }

    public interface OnChangeRequestedOrientation {
        void onRequestedOrientationChanged(int requestedOrientation);
    }
}
