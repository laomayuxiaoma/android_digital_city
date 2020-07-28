//package com.muheda.mhdsystemkit.sytemUtil.uiutil;
//
//import android.content.Context;
//
//import com.lljjcoder.citywheel.CityConfig;
//
///**
// * @author wangfei
// * @date 2019/5/8.
// */
//public class CityPickUtils {
//
//    private CityPickUtils() {
//        throw new UnsupportedOperationException("u can't instantiate me...");
//    }
//
//    public static CityConfig init(Context context){
//        return new CityConfig.Builder(context)
//                .textSize(18)
//                .titleTextColor("#ffffff")
//                .textColor("0xFF585858")
//                .confirTextColor("#037BFF")
//                .cancelTextColor("#037BFF")
//                .province("北京市")
//                .city("北京市")
//                .district("东城区")
//                .visibleItemsCount(7)
//                .provinceCyclic(false)
//                .cityCyclic(false)
//                .districtCyclic(false)
//                .itemPadding(5)
//                .setCityInfoType(CityConfig.CityInfoType.BASE)
//                .setCityWheelType(CityConfig.WheelType.PRO_CITY_DIS)
//                .build();
//    }
//}
