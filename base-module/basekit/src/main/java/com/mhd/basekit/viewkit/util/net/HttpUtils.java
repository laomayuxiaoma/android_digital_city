//package com.mhd.basekit.viewkit.util.net;
//
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//
//import com.example.muheda.functionkit.netkit.http.HttpUtil;
//import com.mhd.basekit.viewkit.util.Common;
//import com.mhd.basekit.viewkit.util.oldUtil.SPUtil;
//import com.muheda.mhdsystemkit.sytemUtil.functionutil.LogUtil;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.CookieStore;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.params.HttpClientParams;
//import org.apache.http.conn.scheme.PlainSocketFactory;
//import org.apache.http.conn.scheme.Scheme;
//import org.apache.http.conn.scheme.SchemeRegistry;
//import org.apache.http.conn.ssl.SSLSocketFactory;
//import org.apache.http.conn.ssl.X509HostnameVerifier;
//import org.apache.http.cookie.Cookie;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.params.BasicHttpParams;
//import org.apache.http.params.CoreConnectionPNames;
//import org.apache.http.params.HttpConnectionParams;
//import org.apache.http.params.HttpParams;
//import org.apache.http.params.HttpProtocolParams;
//import org.apache.http.protocol.HTTP;
//import org.apache.http.util.EntityUtils;
//import org.xutils.http.RequestParams;
//
//import java.io.IOException;
//import java.net.Socket;
//import java.net.UnknownHostException;
//import java.security.KeyManagementException;
//import java.security.KeyStore;
//import java.security.KeyStoreException;
//import java.security.NoSuchAlgorithmException;
//import java.security.UnrecoverableKeyException;
//import java.security.cert.CertificateException;
//import java.security.cert.X509Certificate;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLException;
//import javax.net.ssl.SSLSession;
//import javax.net.ssl.SSLSocket;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//
///**
// * httpclient 封装网络请求
// */
//public class HttpUtils {
//    private static DefaultHttpClient httpClient;
//    private static String JSESSIONID;
//
//    public static HttpClient getHttpClient(String userAgent) {
//        if (httpClient == null) {
//            synchronized (DefaultHttpClient.class) {
//                if (httpClient == null) {
//                    httpClient = setHttpClientParams(userAgent);
//                }
//            }
//        }
//        return httpClient;
//    }
//
//    private static DefaultHttpClient setHttpClientParams(String userAgent) {
//        // 创建 HttpParams 以用来设置 HTTP 参数（这一部分不是必需的）
//        HttpParams httpParams = new BasicHttpParams();
//        // 设置连接超时和 Socket 超时，以及 Socket 缓存大小
//        HttpConnectionParams.setConnectionTimeout(httpParams, 20 * 1000);
//        HttpConnectionParams.setSoTimeout(httpParams, 20 * 1000);
//        HttpConnectionParams.setSocketBufferSize(httpParams, 8192);
//        // 设置重定向，缺省为 true
//        HttpClientParams.setRedirecting(httpParams, true);
//        // 设置 user agent
//        RequestParams param = new RequestParams();
////        String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2) Gecko/20100115 Firefox/3.6";
//        HttpProtocolParams.setUserAgent(httpParams, userAgent);
//
//        KeyStore trustStore = null;
//        // 创建一个 HttpClient 实例
//        // 中的用法，在 Android 1.5 中我们需要使用 Apache 的缺省实现 DefaultHttpClient
//        DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
//        try {
//            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
//            trustStore.load(null, null);
//            SSLSocketFactory sf = new UnVerifySocketFactory(trustStore);
//            SchemeRegistry schReg = new SchemeRegistry();
//            schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
//            schReg.register(new Scheme("https", sf, 443));
//            HttpParams params = httpClient.getParams();
//            httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(
//                    params, schReg), params);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        return httpClient;
//    }
//
//    /**
//     * 核心类
//     * UnVerifySocketFactory:一个验证证书时总是返回true的SSLSocketFactory的子类
//     */
//    private static X509HostnameVerifier ignoreVerifier;
//
//    private static class UnVerifySocketFactory extends SSLSocketFactory {
//        SSLContext sslContext = SSLContext.getInstance("TLS");
//
//        public UnVerifySocketFactory(KeyStore truststore)
//                throws NoSuchAlgorithmException, KeyManagementException,
//                KeyStoreException, UnrecoverableKeyException {
//            super(truststore);
//
//            TrustManager tm = new X509TrustManager() {
//                public void checkClientTrusted(X509Certificate[] chain,
//                                               String authType) throws CertificateException {
//                }
//
//                public void checkServerTrusted(X509Certificate[] chain,
//                                               String authType) throws CertificateException {
//                }
//
//                public X509Certificate[] getAcceptedIssuers() {
//                    return null;
//                }
//            };
//
//            sslContext.init(null, new TrustManager[]{tm}, null);
//        }
//
//        @Override
//        public Socket createSocket(Socket socket, String host, int port,
//                                   boolean autoClose) throws IOException, UnknownHostException {
//            return sslContext.getSocketFactory().createSocket(socket, host,
//                    port, autoClose);
//        }
//
//        //核心代码
//        @Override
//        public void setHostnameVerifier(X509HostnameVerifier hostnameVerifier) {
//            // TODO Auto-generated method stub
//            ignoreVerifier = new X509HostnameVerifier() {
//                @Override
//                public void verify(String arg0, String[] arg1, String[] arg2)
//                        throws SSLException {
//                }
//
//                @Override
//                public void verify(String arg0, X509Certificate arg1)
//                        throws SSLException {
//                }
//
//                @Override
//                public void verify(String arg0, SSLSocket arg1)
//                        throws IOException {
//                }
//
//                //最最核心代码
//                @Override
//                public boolean verify(String arg0, SSLSession arg1) {
//                    return true;
//                }
//            };
//            super.setHostnameVerifier(ignoreVerifier);
//        }
//
//        @Override
//        public X509HostnameVerifier getHostnameVerifier() {
//            return ignoreVerifier;
//        }
//
//        @Override
//        public Socket createSocket() throws IOException {
//            return sslContext.getSocketFactory().createSocket();
//        }
//    }
//
//
//    /**
//     * get 请求
//     */
//    @SuppressWarnings("rawtypes")
//    public static String[] doGet(String url, Map params) throws Exception {
//        url = HttpUtilsFilter.checkUrlAndParams(url, params);
//        HttpGet httpRequest = new HttpGet(getGetUrl(url, params));
//        if (null != JSESSIONID) {
//            httpRequest.setHeader("Cookie", "JSESSIONID=" + JSESSIONID);
//        }
//        // 设置超时时间
//        httpClient.getParams().setParameter(
//                CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
//        /* 发送请求并等待响应 */
//        HttpResponse httpResponse = httpClient.execute(httpRequest);
//        return getResponse(httpResponse, url);
//    }
//
//    /**
//     * post 请求
//     */
//    public static String[] doPost(String url, List<NameValuePair> params)
//            throws Exception {
//        params.add(new BasicNameValuePair("version", Common.version));
//        url = HttpUtilsFilter.checkUrlAndParams(url, params);
//        /* 建立HTTPPost对象 */
//        HttpPost httpRequest = new HttpPost(url);
//        /* 添加请求参数到请求对象 */
//        if (params != null && params.size() > 0) {
//            httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//        }
//        if (null != JSESSIONID) {
//            httpRequest.setHeader("Cookie", "JSESSIONID=" + JSESSIONID);
//        }
//        // 设置超时时间
//        httpClient.getParams().setParameter(
//                CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
//        /* 发送请求并等待响应 */
//        HttpResponse httpResponse = httpClient.execute(httpRequest);
//        return getResponse(httpResponse, url);
//    }
//
//    /**
//     * 网络返回结果
//     */
//    private static String[] getResponse(HttpResponse httpResponse, String url) throws IOException {
//        String[] returnStr = new String[2];
//        String strResult;
//        returnStr[0] = httpResponse.getStatusLine().getStatusCode() + "";
//        /* 若状态码为200 ok */
//        if (httpResponse.getStatusLine().getStatusCode() == 200) {
//            /* 读返回数据 */
//            strResult = EntityUtils.toString(httpResponse.getEntity());
//            returnStr[1] = strResult;
//            Log.i("yyy", returnStr[1]);
//        } else {
//            returnStr[1] = "";
//        }
//        /* 获取cookieStore */
//        CookieStore cookieStore = httpClient.getCookieStore();
//        List<Cookie> cookies = cookieStore.getCookies();
//        Cookie cookie = null;
//        LogUtil.e("Burl:" + url);
//        for (int i = 0; i < cookies.size(); i++) {
//            if ("JSESSIONID".equals(cookies.get(i).getName())) {
//                cookie = cookies.get(i);
//                JSESSIONID = cookie.getValue();
//                HttpUtil.cookie = "JSESSIONID=" + JSESSIONID;
//                Log.d("---cookie---", HttpUtil.cookie);
//                Common.sessionId = cookie.getValue();
//                String cookieString = cookie.getName() + "=" + cookie.getValue() +
//                        ";domain=" + cookie.getDomain();
//                LogUtil.e("Bcookie:" + "name: " + cookie.getName() + " value:" + cookie.getValue() + " damain:" + cookie.getDomain());
//                Common.cookieString = cookieString;
//                SPUtil.setString("Jession", cookie.getValue());
//                break;
//            }
//        }
//        return returnStr;
//    }
//
//    @NonNull
//    private static String getGetUrl(String url, Map params) {
//        String paramStr = "";
//        if (params != null) {
//            Iterator iter = params.entrySet().iterator();
//            while (iter.hasNext()) {
//                Map.Entry entry = (Map.Entry) iter.next();
//                Object key = entry.getKey();
//                Object val = entry.getValue();
//                if (null != val && !"".equals(val)) {
//                    paramStr += paramStr = "&" + key + "=" + val;
//                }
//            }
//        }
//        if (!paramStr.equals("")) {
//            paramStr = paramStr.replaceFirst("&", "?");
//            url += paramStr;
//        }
//        if (url.contains("?")) {
//            url += "&version=" + Common.version;
//        } else {
//            url += "?version=" + Common.version;
//        }
//        return url;
//    }
//}
