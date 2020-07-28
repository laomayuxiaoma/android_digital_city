package com.example.muheda.citylocation.adapter.design;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 13660 on 2019/2/19.
 */

public class CityFactory {
    public static CityFactory mCityFactory=new CityFactory();
    public static final int LOCATION=0;
    public static final int NEAR=1;
    public static final int HOT=2;
    public static final int ALL=3;
    private CityFactory(){

    }


    private static Map<Integer,CityStrategy> strategyMap= new HashMap<>();

    static{
        strategyMap.put(LOCATION,new CityLocationStrategy());
        strategyMap.put(NEAR,new CityNearStrategy());
        strategyMap.put(HOT,new CityHotStrategy());
        strategyMap.put(ALL,new CityAllStrategy());
    }

    public CityStrategy creator(int type){

        return strategyMap.get(type)==null?new CityOtherStrategy():strategyMap.get(type);
    }

    public static CityFactory getInstance(){
        return mCityFactory;
    }
}
