package com.muheda.mdsearchview.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.muheda.mdsearchview.model.MySearchDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangfei
 * @date 2019/11/28.
 */
public class ListDataSave {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public ListDataSave(Context mContext, String preferenceName) {
        preferences = mContext.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    /**
     * 保存List
     *
     * @param tag
     * @param dataList
     */
    public void setDataList(String tag, List<MySearchDto.SearchDto.DataBean> dataList) {
        Gson gson = new Gson();
        String strJson = null;
        if (null == dataList || dataList.size() <= 0) {
        } else {
            //转换成json数据，再保存
            strJson = gson.toJson(dataList);
        }
        editor.clear();
        editor.putString(tag, strJson);
        editor.commit();
    }

    /**
     * 获取List
     *
     * @param tag
     * @return
     */
    public List<MySearchDto.SearchDto.DataBean> getDataList(String tag) {
        List<MySearchDto.SearchDto.DataBean> dataList = new ArrayList<>();
        String strJson = preferences.getString(tag, null);
        if (null == strJson) {
            return dataList;
        }
        Gson gson = new Gson();
        dataList = gson.fromJson(strJson, new TypeToken<List<MySearchDto.SearchDto.DataBean>>() {}.getType());
        return dataList;
    }

}
