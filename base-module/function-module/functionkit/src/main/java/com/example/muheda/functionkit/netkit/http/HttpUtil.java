package com.example.muheda.functionkit.netkit.http;

import android.annotation.SuppressLint;

import com.example.muheda.functionkit.netkit.util.IntenetUtil;
import com.google.gson.Gson;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.CallBack;
import com.zhouyou.http.callback.DownloadProgressCallBack;
import com.zhouyou.http.callback.ProgressDialogCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.model.HttpParams;

import io.reactivex.disposables.Disposable;
import okhttp3.Interceptor;

import static com.example.muheda.functionkit.netkit.http.MHDHttp.getCurrentVersion;

/**
 * Created by 13660 on 2018/5/11.
 */

public class HttpUtil {
    public static String cookie="";
    //get请求 取消网络请求 EasyHttp.cancelSubscription(disposable)
    public static Disposable get(String url, HttpParams httpParams, CallBack callBack) {

        return EasyHttp.get(url).params(httpParams).execute(callBack);
    }
    //baseGet用于基本域名不一致情况下
    public static Disposable baseGet(String baseUrl, String url, HttpParams httpParams, CallBack callBack) {

        return EasyHttp.get(url).baseUrl(baseUrl).params(httpParams).execute(callBack);
    }
    //post请求
    public static Disposable post(String url, HttpParams httpParams, CallBack callBack) {

        return EasyHttp.post(url).params(httpParams).execute(callBack);
    } //delete请求

    @SuppressLint("MissingPermission")
    public static Disposable post(String url, Object o, CallBack callBack) {
        url = url+"?source=Android&version="+getCurrentVersion(EasyHttp.getContext()).versionName+"&appUnique="+MHDHttp.getDeviceId()+"&network="+ IntenetUtil.getNetworkState(EasyHttp.getContext());

        return EasyHttp.post(url).upJson(new Gson().toJson(o)).execute(callBack);
    }

    public static Disposable post(String url, Object o, HttpParams httpParams, CallBack callBack) {

        return EasyHttp.post(url).upJson(new Gson().toJson(o)).params(httpParams).execute(callBack);
    }

    public static Disposable post(String url, HttpParams httpParams, CallBack callBack, Interceptor internal) {

        return EasyHttp.getInstance().addInterceptor(internal).post(url).params(httpParams).execute(callBack);
    }

    public static Disposable delete(String url, HttpParams httpParams, CallBack callBack) {

        return EasyHttp.delete(url).params(httpParams).execute(callBack);
    }

    public static Disposable uploadImage(String url, HttpParams httpParams, ProgressDialogCallBack callBack) {
        return post(url, httpParams, callBack);
    }

    public static Disposable downloadFile(String url, String parentPath, String fileName, final DownloadProgressCallBack callBack) {

        return EasyHttp.downLoad(url)
                .savePath(parentPath)
                .saveName(fileName)//不设置默认名字是时间戳生成的
                .execute(new DownloadProgressCallBack<String>() {
                    @Override
                    public void update(long bytesRead, long contentLength, boolean done) {
//                        int progress = (int) (bytesRead * 100 / contentLength);
                        callBack.update(bytesRead,contentLength,done);
                    }

                    @Override
                    public void onStart() {
                        //开始下载
                        callBack.onStart();
                    }

                    @Override
                    public void onComplete(String path) {
                        //下载完成，path：下载文件保存的完整路径
                        callBack.onComplete(path);
                    }

                    @Override
                    public void onError(ApiException e) {
                        //下载失败
                        callBack.onError(e);
                    }
                });
    }
}
