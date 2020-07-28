package com.example.muheda.citylocation.adapter.design;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.amap.api.location.AMapLocation;
import com.example.amapservice.LocationManager;
import com.example.muheda.citylocation.R;
import com.example.muheda.citylocation.entity.City;
import com.example.muheda.citylocation.utils.LocationInfo;
import com.example.muheda.citylocation.utils.ResultUtil;


import java.util.List;

/**
 * Created by 13660 on 2019/2/19.
 */

public class CityLocationStrategy implements CityStrategy{
    String lCity;
    @Override
    public View doOperator(Context context,View view, LayoutInflater inflater,String lcity,List<String> historyCities,List<City> hotCities,List<City> allCities,int position) {
        return location(context,view,inflater,lcity,historyCities,hotCities);
    }

    public View location(final Context context, View view, LayoutInflater inflater, final String lcity, List<String> historyCities, List<City> hotCities){
        lCity=lcity;
        view=inflater.inflate(R.layout.item_city_location, null);
        final Button tv_location=view.findViewById(R.id.tv_location);
        tv_location.setText(TextUtils.isEmpty(lCity)?"重新定位":lCity);
        tv_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(lCity)){
//                    LocationManager.start(context, new LocationManager.onLocationListener() {
//                        @Override
//                        public void getData(AMapLocation aMapLocation) {
//                            String city = aMapLocation.getCity();
//                            lCity=city;
//                            tv_location.setText(TextUtils.isEmpty(lCity)?"重新定位":lCity);
//                            LocationInfo.lat = aMapLocation.getLatitude()+"";
//                            LocationInfo.lng = aMapLocation.getLongitude()+"";
//                            LocationInfo.areaName = city;
//                        }
//                    });
                }else {
                    ResultUtil.resultBundle(context, lCity, 200);
                }
            }
        });

        return view;
    }
}
