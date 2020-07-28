package com.mhd.basekit.viewkit.view.webview;

import android.content.Context;
import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;

import com.example.muheda.functionkit.netkit.http.HttpUtil;
import com.mhd.basekit.databinding.ActivityWebViewBinding;
import com.muheda.mhdsystemkit.sytemUtil.functionutil.StringUtils;
import com.zhouyou.http.callback.DownloadProgressCallBack;
import com.zhouyou.http.exception.ApiException;

import java.io.File;

import Tool.DialogUtils;

/**
 * @author zhangming
 * @Date 2019/4/2 18:17
 * @Description: 处理home中扩充的webview内容
 */
public class PdfWebViewControl extends WebViewControl {

    public static long START_TIME = 0;//起始时间0毫秒

    private boolean isHanledUrl = false;

    public PdfWebViewControl(Context context) {
        super(context);
    }

    @Override
    public void init(final ActivityWebViewBinding mBinding) {
        initWebView(mBinding);
        if (!getUrl().contains("https://")) {
            setUrl(getUrl().replace("https:/", "https://"));
        }
        String fileName = StringUtils.substring(getUrl(), getUrl().lastIndexOf('/') + 1, getUrl().lastIndexOf('?') != -1 ? getUrl().lastIndexOf('?') : getUrl().length());
        File file = new File(getContext().getCacheDir().getAbsolutePath() + File.separator + fileName);
        if (file.exists() && file.lastModified() == START_TIME) {
            setUrl("file:///android_asset/pdfjs/web/viewer.html?file=" + file.getAbsolutePath());
            return;
        }
        DialogUtils.getInstance().showProgressDialog(getActivity(), DialogUtils.SYSDiaLogType.IosType, "加载中...");
        HttpUtil.downloadFile(getUrl(), getContext().getCacheDir().getAbsolutePath(), fileName, new DownloadProgressCallBack() {
            @Override
            public void update(long bytesRead, long contentLength, boolean done) {
                DialogUtils.getInstance().dismissProgress();
            }

            @Override
            public void onComplete(final String path) {
                mBinding.webcommonWebview.loadUrl("file:///android_asset/pdfjs/web/viewer.html?file=" + path);
                DialogUtils.getInstance().dismissProgress();
                File file = new File(path);
                if (file.exists()) {
                    file.setLastModified(START_TIME);
                }
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onError(ApiException e) {
                DialogUtils.getInstance().dismissProgress();
            }
        });
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
            }
        });
    }

    private void handleUrl(WebView view, String url) {

    }


}