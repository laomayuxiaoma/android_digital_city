package com.example.muheda.citylocation.adapter.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.muheda.citylocation.R;
import com.example.muheda.citylocation.entity.City;

import java.util.List;

/**
 * Created by 13660 on 2019/2/19.
 */

public class CityAllStrategy implements CityStrategy{
    @Override
    public View doOperator(Context context, View view, LayoutInflater inflater, String lcity, List<String> historyCities, List<City> hotCities,List<City> allCities,int position) {
        return inflater.inflate(R.layout.item_city_total_tag, null);
    }
}
