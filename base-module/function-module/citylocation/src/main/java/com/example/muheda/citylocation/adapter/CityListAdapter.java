package com.example.muheda.citylocation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.muheda.citylocation.adapter.design.CityFactory;
import com.example.muheda.citylocation.entity.City;
import com.example.muheda.citylocation.utils.PingYinUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by next on 2016/3/25.
 */
public class CityListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<City> allCities;
    private List<City> hotCities;
    private List<String> historyCities;
    private String[] firstLetterArray;// 存放存在的汉语拼音首字母
    private Map<String, Integer> letterIndex;
    private final int VIEW_TYPE = 5;
    private String lCity = "";

    public CityListAdapter(Context context, List<City> allCities, List<City> hotCities, List<String> historyCities, Map<String, Integer> letterIndex, String lCity) {
        this.context = context;
        this.allCities = allCities;
        this.hotCities = hotCities;
        this.historyCities = historyCities;
        this.letterIndex = letterIndex;
        this.lCity = lCity;
        inflater = LayoutInflater.from(this.context);

        setup();
    }

    public void setCity(String city) {
        this.lCity = city;
    }

    private void setup() {
        firstLetterArray = new String[allCities.size()];
        for (int i = 0; i < allCities.size(); i++) {
            // 当前汉语拼音首字母
            String currentStr = PingYinUtil.getAlpha(allCities.get(i).getPinyin());
            // 上一个汉语拼音首字母，如果不存在为" "
            String previewStr = (i - 1) >= 0 ? PingYinUtil.getAlpha(allCities.get(i - 1).getPinyin()) : " ";
            if (!previewStr.equals(currentStr)) {
                String name = PingYinUtil.getAlpha(allCities.get(i).getPinyin());
                letterIndex.put(name, i);
                firstLetterArray[i] = name;
            }
        }
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE;
    }

    @Override
    public int getItemViewType(int position) {
        return position < 4 ? position : 4;
    }

    @Override
    public int getCount() {
        return allCities.size();
    }

    @Override
    public Object getItem(int position) {
        return allCities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        int viewType = getItemViewType(position);
        convertView = CityFactory.getInstance().creator(viewType).doOperator(context, convertView, inflater, lCity, historyCities, hotCities, allCities, position);
        return convertView;
    }


}
