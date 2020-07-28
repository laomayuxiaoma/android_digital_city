package com.mhd.basekit.viewkit.util.oldUtil;

/**
 * 作者：admin 许长城
 * 2018/4/26 1359
 */
public class MessageVersionDto {
    private String openid;
    private String access_token;
    private String wechatPic;
    private String wxuserName;
    private String wxsex;

    public String getWechatPic() {
        return wechatPic;
    }

    public void setWechatPic(String wechatPic) {
        this.wechatPic = wechatPic;
    }

    public String getWxuserName() {
        return wxuserName;
    }

    public void setWxuserName(String wxuserName) {
        this.wxuserName = wxuserName;
    }

    public String getWxsex() {
        return wxsex;
    }

    public void setWxsex(String wxsex) {
        this.wxsex = wxsex;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
