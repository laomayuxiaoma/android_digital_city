package com.example.muheda.citylocation.adapter.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.example.muheda.citylocation.R;
import com.example.muheda.citylocation.adapter.RecentCityAdapter;
import com.example.muheda.citylocation.entity.City;

import java.util.List;

/**
 * Created by 13660 on 2019/2/19.
 */

public class CityNearStrategy implements CityStrategy{
    @Override
    public View doOperator(Context context, View view, LayoutInflater inflater, String lcity,List<String> historyCities,List<City> hotCities,List<City> allCities,int position) {
       return  near(context,view,inflater,lcity,historyCities,hotCities);
    }

    public View near(Context context, View view, LayoutInflater inflater, String lcity,List<String> historyCities,List<City> hotCities){
        view = inflater.inflate(R.layout.item_city_grid, null);
        GridView recentCityView = (GridView) view.findViewById(R.id.grid_city);
        recentCityView.setAdapter(new RecentCityAdapter(context, historyCities));
        TextView recentHint = (TextView) view.findViewById(R.id.recentHint);
        recentHint.setText("最近访问的城市");
        return view;
    }
}
