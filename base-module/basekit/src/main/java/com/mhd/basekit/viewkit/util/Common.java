package com.mhd.basekit.viewkit.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import com.google.gson.Gson;
import com.mhd.basekit.model.Login.LoginDto;
import com.mhd.basekit.viewkit.mvp.contract.model.user.UserInfoDto;
import com.mhd.basekit.viewkit.util.oldUtil.BaseInit;
import com.muheda.mhdsystemkit.sytemUtil.functionutil.MMKVUtil;
import com.zhouyou.http.EasyHttp;

public class Common {
    /**
     * 登录成功用户token
     */
    public static String sessionId = "";
    public static String cookieString = "";
    public static final String SAVE_USER = "UserInfoDto";
    public static LoginDto user = null;

    private static String wxAPPid = "";


    public static String getWxAPPid() {
        return wxAPPid;
    }

    public static void setWxAPPid(String wxAPPid) {
        Common.wxAPPid = wxAPPid;
    }

    /**
     * 用户信息
     */
    public static Activity activity = null;

//    public static final String version = Common.getCurrentVersion(BaseInit.getApplication());

    public static UserInfoDto userInfo;

    private static String deviceId;
    private static int deviceType;

    /**
     * 正式服务器
     */

    //        public static String url = "https://passport.muheda.com";//认证中心
    //        public static String scoreUrl = "http://center.dtbpoint.com";//点通宝
    //        public static String mallurl = "https://shop.muheda.com";//商城
    //        public static String carUrl4 = "http://idsw.muheda.com/IDSW/";
    //        public static String carUrl5 = "https://4s.muheda.com/";
    //        public static String DevieceMsg = "http://www.database88.com/";//设备空气净化器列表
    //        public static String LANDURL = "https://server.muheda.com";
    //
    //
    //        public static String DevieceMsg_Legn = "http://coldchain.muheda.com";//设备冷链接口
    //        public static String MHD_DEVICES = "https://client.muheda.com/";

    /**测试仿真3307服务器*/

    //        public static String mallurl = "https://shoptest.muheda.com";
    //       public static String carUrl5 = "https://4stest.muheda.com";
    //       public static String url = "https://passporttest.muheda.com";
    //        public static String LANDURL = "http://servertest.muheda.com";
    //        public static String MHD_DEVICES = "http://clienttest.muheda.com/";
    //
    //        public static String scoreUrl = "http://120.76.31.87:8084/";//点通宝
    //        public static String DevieceMsg_Legn = "http://coldchaintest.muheda.com";//设备冷链接口
    //        public static String carUrl4 = "http://idswtest.muheda.com/IDSW/";
    //        public static String DevieceMsg = "http://qtlrtest.muheda.com/";//设备空气净化器列表

    /**
     * 测试仿真3308服务器
     */


    public static String mallurl = "https://shoptemporary.muheda.com";
    public static String carUrl5 = "https://4stemporary.muheda.com";
    public static String url = "https://passporttemporary.muheda.com";
    public static String LANDURL = "http://servertemporary.muheda.com";
    public static String MHD_DEVICES = "http://clienttemporary.muheda.com/";

    public static String scoreUrl = "http://120.76.31.87:8084/";//点通宝
    public static String DevieceMsg_Legn = "http://coldchaintemporary.muheda.com";//设备冷链接口
    public static String carUrl4 = "http://idswtemporary.muheda.com/IDSW/";
    public static String DevieceMsg = "http://qtlrtemporary.muheda.com/";//设备空气净化器列表
    public static String carSafe = "http://idswtemporary.muheda.com/datambidas/";//安全行车数据传输

    /**
     * 测试内网服务器
     */
    /*public static String mallurl = "https://shopceshi.muheda.com";
    public static String carUrl5 = "https://4sceshi.muheda.com";
    //    public static String carUrl5 = "https://db4stest.muheda.com";
    public static String url = "https://passportceshi.muheda.com";
    public static String LANDURL = "https://serverceshi.muheda.com";//测试*/

    //无变化服务
    //    public static String scoreUrl = "http://120.76.31.87:8084/";//点通宝
    //    public static String carUrl4 = "http://172.17.3.174:8765/IDSW/";


    /**
     * 获取当前应用的版本号
     */
    public static String getCurrentVersion(Context mContext) {
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = mContext.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(
                    mContext.getPackageName(), 0);
            return packInfo.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }


    public static UserInfoDto getUserInfo() {
        return userInfo;
    }

    public static String getUuid() {
        return userInfo == null ? "" : userInfo.getData().getUuid();
    }

    public static String getId() {
        return userInfo == null ? "" : userInfo.getData().getId();
    }

    public static void setUserInfo(UserInfoDto userInfo) {
        MMKVUtil.putString(SAVE_USER, userInfo == null ? "" : new Gson().toJson(userInfo));
        Common.userInfo = userInfo;
    }

    public static String getDeviceId() {
        return deviceId;
    }

    public static void setDeviceId(String deviceId) {
        Common.deviceId = deviceId;
    }

    public static int getDeviceType() {
        return deviceType;
    }

    public static void setDeviceType(int deviceType) {
        Common.deviceType = deviceType;
    }

    public static LoginDto getUser() {
        return user;
    }

    public static void setUser(LoginDto user) {
        Common.user = user;
        EasyHttp.getInstance().getCommonParams().put("token", Common.getToken());

    }

    public static String getToken() {
        if (user == null || user.getData() == null) {
            return "";
        }
        return user.getData().getToken();
    }
}
