package com.mhd.basekit.viewkit.util;


import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/**
 * @author zhangming
 * @Date 2019/3/29 14:27
 * @Description: 忽略证书
 */
public class IgnoreTheCertificate {

    public static SSLSocketFactory ignoreTheCertificate() {
        X509TrustManager trustManager;
        SSLSocketFactory sslSocketFactory = null;
        trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws
                    CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };

        try {
            SSLContext sslContext;
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new X509TrustManager[]{trustManager}, null);
            sslSocketFactory = sslContext.getSocketFactory();
            return sslSocketFactory;
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    public static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
}
