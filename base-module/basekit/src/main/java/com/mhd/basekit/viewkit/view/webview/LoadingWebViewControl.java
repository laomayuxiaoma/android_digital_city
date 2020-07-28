package com.mhd.basekit.viewkit.view.webview;

import android.content.Context;
import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;

import com.mhd.basekit.databinding.ActivityWebViewBinding;
import com.mhd.basekit.viewkit.view.dialog.GlobalLoadDialogShow;

/**
 * @author zhangming
 * @Date 2019/4/2 18:17
 * @Description: 扩充的webview内容(含有Loading)
 */
public class LoadingWebViewControl extends WebViewControl {

    public static long START_TIME = 0;//起始时间0毫秒

    private boolean isHanledUrl = false;
    private static boolean isFrist = true;

    public LoadingWebViewControl(Context context) {
        super(context);
    }

    @Override
    public void init(final ActivityWebViewBinding mBinding) {
        isFrist = true;
        initWebView(mBinding);
        if (isFrist){
            getActivity().showProgressDialog();
        }
    }

    @Override
    public void onFirstLoadFinashed() {

    }


    private void initWebView(final ActivityWebViewBinding mBinding) {
        //Android WebView 软键盘挡住输入框(添加此方法都行了)
        mBinding.webcommonWebview.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (isHanledUrl) {
                    isHanledUrl = false;
                    return super.shouldOverrideUrlLoading(view, url);
                }
                handleUrl(view, url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                isHanledUrl = true;
                handleUrl(view, request.getUrl().toString());
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (isFrist){
                    isFrist = false;
                    getActivity().hideProgressDialog(GlobalLoadDialogShow.NET_STATE_DISMISS);
                }
            }
        });
    }

    private void handleUrl(WebView view, String url) {

    }


}