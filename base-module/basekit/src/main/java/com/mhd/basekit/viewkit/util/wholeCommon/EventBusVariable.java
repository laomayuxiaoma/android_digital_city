package com.mhd.basekit.viewkit.util.wholeCommon;

/**
 * Created by 13660 on 2019/4/1.
 */

public class EventBusVariable {
    //eventbus类型变量
    public static final int EXIT = 10000;//退出操作
    public static final int LOGIN = 10001;//登陆判断,跳转事件继续
    public static final int IS_LOGIN = 10002;//用户登录

    public static final int PAY_SUCCESS = 1;//支付成功



    //推荐页面以100开头后跟四位
    public static final int TAB_CHANGE = 1000000;//首页tab切换
    public static final int PATH_BACK = 1000001;//路径返回
    public static final int SELECT_TAB = 1000002;//条件选择
    public static final int HOME_LIST = 1000003;//首页请求
    public static final int MAP_PATH = 1000004;//路径搜索
    public static final int MAP_STATION = 1000005;//站点列表
    public static final int NO_LOCATION = 1000006;//无定位权限
    public static final int CITY_CHANGE = 1000007;//城市选择改变
    public static final int CITY_CHANGE_DATA = 1000008;//城市选择改变需要刷新数据
    public static final int MAP_PATH_INFO = 1000009;//路径信息
    public static final int MAP_PATH_STATION_UP = 1000010;//添加途经点弹框
    public static final int MAP_OTHER_CLICK = 1000011;//地图空白处点击
    public static final int PUSH_NOTIFICATION = 1000012;//充电状态推送
    public static final int SCAN_FINASH = 1000013;//退出扫码页
    public static final int ORDER_REFRESH = 1000014;//退出扫码页

}
