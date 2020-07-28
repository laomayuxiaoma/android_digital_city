package com.example.muheda.citylocation.adapter.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.muheda.citylocation.entity.City;

import java.util.List;

/**
 * Created by 13660 on 2019/2/19.
 */

public interface CityStrategy {
    public View doOperator(Context context, View view, LayoutInflater inflater, String lcity,List<String> historyCities,List<City> hotCities,List<City> allCities,int position);
}
