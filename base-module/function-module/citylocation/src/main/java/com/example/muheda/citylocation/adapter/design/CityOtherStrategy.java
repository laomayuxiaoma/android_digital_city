package com.example.muheda.citylocation.adapter.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.muheda.citylocation.R;
import com.example.muheda.citylocation.entity.City;
import com.example.muheda.citylocation.utils.PingYinUtil;

import java.util.List;

/**
 * Created by 13660 on 2019/2/19.
 */

public class CityOtherStrategy implements CityStrategy{
    @Override
    public View doOperator(Context context, View view, LayoutInflater inflater, String lcity, List<String> historyCities, List<City> hotCities,List<City> allCities,int position) {
        return other(context,view,inflater,lcity,historyCities,hotCities,allCities,position);
    }

    public View other(Context context, View view, LayoutInflater inflater, String lcity, List<String> historyCities, List<City> hotCities,List<City> allCities,int position){
        Holder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_city_list, null);
            holder = new Holder();
            holder.letter = (TextView) view.findViewById(R.id.tv_letter);
            holder.name = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        if (position >= 1) {
            holder.name.setText(allCities.get(position).getName());
            String currentStr = PingYinUtil.getAlpha(allCities.get(position).getPinyin());
            String previewStr = (position - 1) >= 0 ? PingYinUtil.getAlpha(allCities.get(position - 1).getPinyin()) : " ";
            if (!previewStr.equals(currentStr)) {
                holder.letter.setVisibility(View.VISIBLE);
                holder.letter.setText(currentStr);
            } else {
                holder.letter.setVisibility(View.GONE);
            }
        }
        return view;
    }

    class Holder {
        TextView letter, name;
    }


}
