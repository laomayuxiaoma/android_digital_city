package com.mhd.basekit.viewkit.util.wholeCommon;

/**
 * Created by 13660 on 2019/4/1.
 */

public class UrlConstant {

    //域名配置
//    public static final String HTTP_URL = "https://platform.syevdc.com/operatorPlatform/";
//    public static final String HTTP_WEB_URL = "platformh5.syevdc.com/RenaultApp";

    //测试
//    public static final String HTTP_URL = "http://172.18.10.201:6085/operatorPlatform/";
    public static final String HTTP_URL = "https://clientxnytemporary.muheda.com/operatorPlatform/";
    public static final String HTTP_WEB_URL = "https://clienttemporary.muheda.com/RenaultApp";


    //home 首页
    public static final String HTTP_HOME_DATA = "app/home/v1/info";
    public static final String HTTP_HOT_DATA = "app/activity/v1/hot";
    public static final String HTTP_USER_DATA = "app/user/v1/setting";

    public static final String HTTP_HOME_ORDER_SETTLE = "app/order/v1/settlement_init";
    public static final String HTTP_HOME_ORDER_COMMIT = "app/order/v1/settlement";

    public static final String HTTP_HOME_ALI_PAY = "app/pay/v1/ali";
    public static final String HTTP_HOME_WX_PAY = "app/pay/v1/wx";


    //me 我的页面
    public static final String ME_SEND_SMS = "app/account/sendSms"; //获取验证码
    public static final String ME_LOGIN = "app/account/login"; //APP手机登录
    public static final String ME_LOGOUT = "app/account/logout"; //APP退出登录
    public static final String ME_IDEA = "app/user/feedback/insert"; //APP退出登录


    //推荐模块
    public static final String RECOMMEND_FILTER = "app/common/query/filter"; //筛选项
    public static final String RECOMMEND_LIST = "app/station/list"; //APP站点列表/地图 APP站点检索列表 APP站点搜索
    public static final String RECOMMEND_STATION_DETAIL = "app/station/detail"; //app站点详情
    public static final String RECOMMEND_PILE_DETAIL = "app/station/pile/detail"; //app电桩详情
    public static final String RECOMMEND_PILE_INFO = "app/pile/pileConnInfoBySid"; //app电桩详情
    public static final String RECOMMEND_COLLECT_ADD = "app/collect/add"; //APP站点收藏
    public static final String RECOMMEND_COLLECT_DELETE = "app/collect/delete"; //APP收藏删除
    public static final String RECOMMEND_COLLECT_LIST = "app/collect/list"; //APP收藏列表
    public static final String RECOMMEND_CHARGING_LIST = "app/station/charging/list"; //app收费标准列表
    public static final String RECOMMEND_SEARCH_BY_NAM = "app/station/searchByName"; //APP站点搜索
    public static final String RECOMMEND_STATION_SEARCH = "app/station/search"; //app导航站点列表
    public static final String RECOMMEND_LOAD_BOOK = "app/station/detail"; //路书
    public static final String RECOMMEND_CITY_LIST = "app/common/area/list"; //获取城市列表


    //webview
    public static final String CONSEAL_AGREEMENT = "/#/ChargingApp/protocol_user_secrecy"; //隐私协议
    public static final String USER_AGREEMENT = "/#/ChargingApp/protocol_user_use"; //用户协议


    //charge 充电
    public static final String CHARGE_STATUS = "app/charge/status"; //充电详情
    public static final String CHARGE_STOP = "app/charge/stop"; //停止充电
    public static final String CHARGE_ORDER_LIST = "app/charge/orderList"; //获取订单列表
    public static final String CHARGE_ORDER_DELETE = "app/charge/orderDelete"; //删除订单
    public static final String CHARGE_ORDER_DETAIL = "app/charge/orderDetail"; //订单详情
    public static final String CHARGE_CONNECTOR_EXIST = "app/pile/connectorExist"; //根据企业id和枪编码查询枪的状态
    public static final String WX_PAY = "app/charge/start"; //获取微信支付信息


}
