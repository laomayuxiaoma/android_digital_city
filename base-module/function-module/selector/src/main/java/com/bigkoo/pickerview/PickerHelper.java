package com.bigkoo.pickerview;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import com.bigkoo.pickerview.bean.JsonBean;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnAdressSelectListener;
import com.bigkoo.pickerview.listener.OnOneOptionSelectListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.utils.GetJsonDataUtil;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WF
 * https://github.com/Bigkoo/Android-PickerView
 */
public final class PickerHelper {

    public enum Type {
        YMD, HMS, ALL
    }

    private PickerHelper() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static TimePickerBuilder init(Context context, OnTimeSelectListener onTimeSelectListener) {
        return new TimePickerBuilder(context, onTimeSelectListener);
    }

    public static void initTimePicker(Context context, OnTimeSelectListener onTimeSelectListener) {
        TimePickerView pvTime = build(context, onTimeSelectListener, new boolean[]{true, true, true, false, false, false});
        setStyle(pvTime);
        pvTime.show();
    }

    public static void initTimePicker(Context context, OnTimeSelectListener onTimeSelectListener, Type type) {//Dialog 模式下，在底部弹出
        TimePickerView pvTime = null;
        switch (type) {
            case YMD:
                pvTime = build(context, onTimeSelectListener, new boolean[]{true, true, true, false, false, false});
                break;
            case HMS:
                pvTime = build(context, onTimeSelectListener, new boolean[]{false, false, false, true, true, true}); //时分秒

                break;
            case ALL:
                pvTime = build(context, onTimeSelectListener, new boolean[]{true, true, true, true, true, true}); //年月日时分秒

                break;
        }
        setStyle(pvTime);
        pvTime.show();
    }

    public static void initTimePicker(TimePickerBuilder timePickerBuilder) {//Dialog 模式下，在底部弹出
        TimePickerView pvTime = timePickerBuilder.build();
        setStyle(pvTime);
        pvTime.show();
    }

    public static TimePickerView build(Context context, OnTimeSelectListener onTimeSelectListener, boolean[] bolArray) {
        return init(context, onTimeSelectListener)
                .setType(bolArray) //年月日
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
                .setTitleSize(20)//标题文字大小
                .setTitleText("")//标题文字
                .setDividerColor(Color.BLACK)//设置分割线颜色
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .build();
    }

    private static void setStyle(TimePickerView pvTime) {
        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
    }

    /**
     * .setType(new boolean[]{true, true, true, true, true, true})// 默认全部显示
     * .setCancelText("Cancel")//取消按钮文字
     * .setSubmitText("Sure")//确认按钮文字
     * .setContentSize(18)//滚轮文字大小
     * .setTitleSize(20)//标题文字大小
     * .setTitleText("Title")//标题文字
     * .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
     * .isCyclic(true)//是否循环滚动
     * .setTitleColor(Color.BLACK)//标题文字颜色
     * .setSubmitColor(Color.BLUE)//确定按钮文字颜色
     * .setCancelColor(Color.BLUE)//取消按钮文字颜色
     * .setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
     * .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
     * .setDate(selectedDate)// 如果不设置的话，默认是系统时间
     * .setRangDate(startDate,endDate)//起始终止年月日设定
     * .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
     * .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
     * .isDialog(true)//是否显示为对话框样式
     * .setDividerColor()//设置分割线颜色
     */


    public static void showOnePickerView(Context context, final OnOneOptionSelectListener onOneOptionSelectListener, final List<String> options1Items, String title) {// 弹出选择器

        showOnePickerView(context, onOneOptionSelectListener, null, options1Items, title);
    }

    public static void showOnePickerView(Context context, final OnOneOptionSelectListener onOneOptionSelectListener, View.OnClickListener cancelListener, final List<String> options1Items, String title) {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                onOneOptionSelectListener.onOptionsSelect(options1Items.get(options1), options1);
            }
        }, cancelListener)
                .setTitleText(title)
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setSubCalSize(18)//确定和取消文字大小
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)//滚轮文字大小
                .setOutSideCancelable(false)//点击外部dismiss default true
                .build();

        pvOptions.setPicker(options1Items);//一级选择器

        pvOptions.show();
    }

    public static void showOnePickerView(OptionsPickerBuilder optionsPickerBuilder) {
        OptionsPickerView pvOptions = optionsPickerBuilder.build();
        pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.show();
    }


    public static void showPickerView(Context context, OnAdressSelectListener onAdressSelectListener, String title) {// 弹出选择器
        initJsonData(context, onAdressSelectListener, title);
    }


    /**
     * .setSubmitText("确定")//确定按钮文字
     * .setCancelText("取消")//取消按钮文字
     * .setTitleText("城市选择")//标题
     * .setSubCalSize(18)//确定和取消文字大小
     * .setTitleSize(20)//标题文字大小
     * .setTitleColor(Color.BLACK)//标题文字颜色
     * .setSubmitColor(Color.BLUE)//确定按钮文字颜色
     * .setCancelColor(Color.BLUE)//取消按钮文字颜色
     * .setTitleBgColor(0xFF333333)//标题背景颜色 Night mode
     * .setBgColor(0xFF000000)//滚轮背景颜色 Night mode
     * .setContentTextSize(18)//滚轮文字大小
     * .setLinkage(false)//设置是否联动，默认true
     * .setLabels("省", "市", "区")//设置选择的三级单位
     * .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
     * .setCyclic(false, false, false)//循环与否
     * .setSelectOptions(1, 1, 1)  //设置默认选中项
     * .setOutSideCancelable(false)//点击外部dismiss default true
     * .isDialog(true)//是否显示为对话框样式
     * .isRestoreItem(true)//切换时是否还原，设置默认选中第一项
     */


    private static ArrayList<JsonBean> options1Items = new ArrayList<>();
    private static ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private static ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    //解析 地址jason 的方法
    private static void initJsonData(Context context, final OnAdressSelectListener onAdressSelectListener, String title) {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(context, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

        OptionsPickerView pvOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

                onAdressSelectListener.onOptionsSelect(options1Items.get(options1).getPickerViewText(), options2Items.get(options1).get(options2), options3Items.get(options1).get(options2).get(options3), v);
            }
        }) //TODO
                .setTitleText(title)
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    public static ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            for (int i = 0; i < data.length(); i++) {
                JsonBean jsonBean = new JsonBean();
                List<JsonBean.CityBean> list = new ArrayList<>();
                JSONObject jsonObject = new JSONObject(data.optJSONObject(i).toString());
                jsonBean.setName(jsonObject.getString("name"));
                JSONArray jsonArray = jsonObject.getJSONArray("city");
                for (int j = 0; j < jsonArray.length(); j++) {
                    JSONObject object = jsonArray.getJSONObject(j);
                    JsonBean.CityBean cityBean = new JsonBean.CityBean();
                    cityBean.setName(object.getString("name"));
                    JSONArray jsonArray1 = object.getJSONArray("area");
                    List<String> area = new ArrayList<>();
                    for (int k = 0; k < jsonArray1.length(); k++) {
                        Object object1 = jsonArray1.get(k);
                        area.add(String.valueOf(object1));
                    }
                    cityBean.setArea(area);
                    list.add(cityBean);
                }
                jsonBean.setCityList(list);
                detail.add(jsonBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }
}
