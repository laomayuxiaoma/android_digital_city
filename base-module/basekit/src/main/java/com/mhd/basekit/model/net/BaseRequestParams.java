package com.mhd.basekit.model.net;

import com.google.gson.Gson;
import com.mhd.basekit.viewkit.util.wholeCommon.UserConstant;

import org.apaches.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BaseRequestParams<T> {

    private String charset = "utf-8";
    private String clientInfo = "ANDROID" + android.os.Build.VERSION.RELEASE + android.os.Build.BRAND;
    private String partnerId = "10100001";
    private String signType = "sha256";
    private Long timestamp = System.currentTimeMillis();
    private String token = UserConstant.getTOKEN();
    private Long userId = UserConstant.getUSER_ID();
    private T data;
    private String sign = "";


    public BaseRequestParams sha() {
        StringBuilder sb = new StringBuilder();
        sb.append("charset=").append(this.charset);
        sb.append("&clientInfo=").append(this.clientInfo);
        if (data != null) {
            sb.append("&data=").append(new Gson().toJson(this.data));
        } else {
            sb.append("&data=").append(new Gson().toJson(new Object()));
        }
        sb.append("&key=").append("d44c2f905c6644fdb2fedc8bdef9f83e570258a3152744f6a7c528a38b17d513");
        sb.append("&partnerId=").append(this.partnerId);
        sb.append("&signType=").append(this.signType);
        sb.append("&timestamp=").append(this.timestamp);
        sb.append("&token=").append(this.token);
        sb.append("&userId=").append(this.userId);
//        LogUtils.e("---okhttp加密前---" + sb.toString())
        //生成签名哈希字符串
        this.sign = getSHA256Str(sb.toString());
        return this;
    }

    public String getSHA256Str(String str) {
        MessageDigest messageDigest;
        String encdeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            encdeStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encdeStr;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
