//package com.mhd.basekit.viewkit.util.net;
//
//import android.text.TextUtils;
//
//import com.mhd.basekit.viewkit.util.Common;
//import com.mhd.basekit.viewkit.util.oldUtil.SPUtil;
//
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
//import org.xutils.common.util.KeyValue;
//import org.xutils.http.RequestParams;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 主要用于地址的过滤
// * <p>
// * Created by zhangming on 2018/7/10.
// */
//
//public class HttpUtilsFilter {
//
//    private static String[] urls = new String[]{Common.url, Common.mallurl, Common.carUrl5};
//
//    public static String checkUrlAndParams(String url, Map params) {
//        if (!TextUtils.isEmpty(url) && check(url)) {
//            url = checkUrl(url);
//        }
//        if (check(url) && params != null) {
//            if (params.containsKey("uuid")) {
//                params.remove("uuid");
//                params.put("t", Common.getToken());
//            } else if (params.containsKey("userName") && params.containsKey("password")) {
//                params.remove("userName");
//                params.remove("password");
//                params.put("t", Common.getToken());
//            }
//        }
//        return url;
//    }
//
//    public static String checkUrlAndParams(String url, List<NameValuePair> params) {
//        if (!TextUtils.isEmpty(url) && check(url)) {
//            url = checkUrl(url);
//        }
//        if (check(url) && params != null && checkParams(params)) {
//            params.add(new BasicNameValuePair("t", Common.getToken()));
//        }
//        return url;
//    }
//
//    public static void checkUrlAndParams(String url, RequestParams params) {
//        if (!TextUtils.isEmpty(url) && check(url)) {
//            url = checkUrl(url);
//            params.setUri(url);
//        }
//        if (check(url) && params != null) {
//            if (checkParamsKeyValue(params.getQueryStringParams())) {
//                params.removeParameter("uuid");
//                params.addBodyParameter("t", Common.getToken());
//            } else if (checkParamsKeyValueUserName(params.getQueryStringParams())) {
//                params.removeParameter("userName");
//                params.removeParameter("password");
//                params.addBodyParameter("t", Common.getToken());
//            }
//
//        }
//    }
//
//    //检查WebView中的url
//    public static String checkWebUrl(String url) {
//        if (!TextUtils.isEmpty(url) && check(url)) {
//            url = checkUrl(url);
//        }
//        return url;
//    }
//
//    //检查url是否需要替换
//    private static String checkUrl(String url) {
//        //测试
////        url = ceshi(url);
//
//        if (null != Common.user && (url.contains("uuid=" + Common.user.getUuid())
//                || url.contains(getUserNameAndPassword()) || url.contains(getUserNameAndPasswordTwo()))) {
//            url = url.replaceAll(getUserNameAndPassword(), "t=" + Common.getToken());
//            url = url.replaceAll(getUserNameAndPasswordTwo(), "t=" + Common.getToken());
//            return url.replaceAll("uuid=" + Common.user.getUuid(), "t=" + Common.getToken());
//        }
//        return url;
//    }
//
//    //获取用户名和密码的拼接
//    private static String getUserNameAndPassword() {
//        return "userName=" + SPUtil.getString("userName", "") +
//                "&password=" + MD5Util.getMD5String(SPUtil.getString("password", ""));
//    }
//
//    //获取用户名和密码的拼接
//    private static String getUserNameAndPasswordTwo() {
//        return "userName=" + SPUtil.getString("userName", "") +
//                "&password=" + MD5Util.MD5Encode(SPUtil.getString("password", ""),null);
//    }
//
//    //判断地址是否需要处理
//    private static boolean check(String url) {
//
//        if (!TextUtils.isEmpty(url) && isContainsUrls(url)/*(url.contains(Common.url)
//                || url.contains(Common.mallurl) || url.contains(Common.carUrl5))*/) {
//            return true;
//        }
//        return false;
//    }
//
//    private static boolean isContainsUrls(String url) {
//        int size = urls.length;
//        for (int i = 0; i < size; i++) {
//            if (url.contains(urls[i])) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private static boolean checkParams(List<NameValuePair> params) {
//        for (int i = 0; i < params.size(); i++) {
//            if ("uuid".equalsIgnoreCase(params.get(i).getName())) {
//                params.remove(i);
//                return true;
//            } else if("userUuid".equalsIgnoreCase(params.get(i).getName())){
//                params.remove(i);
//                return true;
//            }else if ("userName".equalsIgnoreCase(params.get(i).getName()) && checkPassword(params)) {
//                params.remove("userName");
//                params.remove("password");
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private static boolean checkPassword(List<NameValuePair> params) {
//        for (int i = 0; i < params.size(); i++) {
//            if ("password".equalsIgnoreCase(params.get(i).getName())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private static boolean checkParamsKeyValue(List<KeyValue> params) {
//        for (int i = 0; i < params.size(); i++) {
//            if ("uuid".equalsIgnoreCase(params.get(i).key)) {
//                params.remove(i);
//                return true;
//            }else if ("userUuid".equalsIgnoreCase(params.get(i).key)) {
//                params.remove(i);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private static boolean checkParamsKeyValueUserName(List<KeyValue> params) {
//        for (int i = 0; i < params.size(); i++) {
//            if ("userName".equalsIgnoreCase(params.get(i).key) && checkParamsKeyValuePassword(params)) {
//                params.remove(i);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private static boolean checkParamsKeyValuePassword(List<KeyValue> params) {
//        for (int i = 0; i < params.size(); i++) {
//            if ("password".equalsIgnoreCase(params.get(i).key)) {
//                params.remove(i);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private static String ceshi(String url) {
//        return url.replaceAll("https", "http");
//    }
//
//}
