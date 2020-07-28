package com.example.muheda.citylocation.adapter.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.example.muheda.citylocation.R;
import com.example.muheda.citylocation.adapter.HotCityAdapter;
import com.example.muheda.citylocation.entity.City;

import java.util.List;

/**
 * Created by 13660 on 2019/2/19.
 */

public class CityHotStrategy implements CityStrategy{
    @Override
    public View doOperator(Context context, View view, LayoutInflater inflater, String lcity, List<String> historyCities,List<City> hotCities,List<City> allCities,int position) {
        return hot(context,view,inflater,lcity,historyCities,hotCities);
    }

    public View hot(Context context, View view, LayoutInflater inflater, String lcity, List<String> historyCities,List<City> hotCities){
        view = inflater.inflate(R.layout.item_city_grid, null);
        final GridView hotCity = (GridView) view.findViewById(R.id.grid_city);
        hotCity.setAdapter(new HotCityAdapter(context, hotCities));
        TextView hotHint = (TextView) view.findViewById(R.id.recentHint);
        hotHint.setText("热门城市");
        return view;
    }
}
