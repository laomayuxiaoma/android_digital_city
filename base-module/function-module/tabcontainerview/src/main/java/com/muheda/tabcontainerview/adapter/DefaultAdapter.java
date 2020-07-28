package com.muheda.tabcontainerview.adapter;

import com.muheda.tabcontainerview.Model.BaseDto;
import com.muheda.tabcontainerview.Model.DefaultDto;
import com.muheda.tabcontainerview.widget.AbsTab;
import com.muheda.tabcontainerview.widget.DefaultTab;


public class DefaultAdapter extends BaseAdapter {

    private String[] mTextArray;
    private int mTextColor, mSelectedTextColor;
    private int[] mIconImageArray;
    private int[] mSelectedIconImageArray;

    public DefaultAdapter(DefaultDto defaultDto) {
        super(new BaseDto(defaultDto.getContext(), defaultDto.getFragmentArray(), defaultDto.getFragmentManager()));
        mTextArray = defaultDto.getTextArray();
        mTextColor = defaultDto.getTextColor();
        mSelectedTextColor = defaultDto.getSelectTextColor();
        mIconImageArray = defaultDto.getIconImageArray();
        mSelectedIconImageArray = defaultDto.getSelectedIconImageArray();
    }


    @Override
    public AbsTab getTab(int index) {
        DefaultTab defaultTab = new DefaultTab(mContext, index);
        defaultTab.setText(mTextArray[index]);
        defaultTab.setTextColor(mTextColor, mSelectedTextColor);
        defaultTab.setIconImage(mIconImageArray[index], mSelectedIconImageArray[index]);
        return defaultTab;
    }

}
