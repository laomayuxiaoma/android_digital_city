package com.mhd.basekit.viewkit.util.wholeCommon;

import com.example.amapservice.Location;
import com.example.muheda.functionkit.netkit.util.IntenetUtil;
import com.muheda.mhdsystemkit.sytemUtil.formatutil.TypeConversionUtils;
import com.zaaach.citypicker.db.DBManager;
import com.zaaach.citypicker.model.City;

/**
 * Created by 13660 on 2019/4/1.
 */

public class UserConstant {
    //数据常量
    private static String TOKEN = "";
    //用户id
    private static Long USER_ID = -1L;

    public static String getTOKEN() {
        return TOKEN;
    }

    public static Long getUSER_ID() {
        return USER_ID;
    }

    public static void setTOKEN(String TOKEN) {
        UserConstant.TOKEN = TOKEN;
    }

    public static void setUSER_ID(Long USER_ID) {
        UserConstant.USER_ID = USER_ID;
    }

    public static Integer getAreaId(){
        String name;
        if (Location.isCurrLocation()){
            name = Location.city;
        }else {
            name = Location.selectedCity;
        }
        City city = DBManager.selecetCity(name);
        if (city != null){
            return TypeConversionUtils.convertToInt(city.getProvince(),-1) ;
        }
        return -1;
    }
}
