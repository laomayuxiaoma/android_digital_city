package com.muheda.tabcontainerview.Model;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * @author wangfei
 * @date 2019/8/13.
 */
public class DefaultDto extends BaseDto {

    private String[] textArray;
    private int textColor;
    private int selectTextColor;
    private int[] iconImageArray;
    private int[] selectedIconImageArray;

    public DefaultDto(Context context, Fragment[] fragmentArray, FragmentManager fragmentManager, String[] textArray, int textColor, int selectTextColor, int[] iconImageArray, int[] selectedIconImageArray) {
        super(context, fragmentArray, fragmentManager);
        this.textArray = textArray;
        this.textColor = textColor;
        this.selectTextColor = selectTextColor;
        this.iconImageArray = iconImageArray;
        this.selectedIconImageArray = selectedIconImageArray;
    }

    public String[] getTextArray() {
        return textArray;
    }

    public void setTextArray(String[] textArray) {
        this.textArray = textArray;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getSelectTextColor() {
        return selectTextColor;
    }

    public void setSelectTextColor(int selectTextColor) {
        this.selectTextColor = selectTextColor;
    }

    public int[] getIconImageArray() {
        return iconImageArray;
    }

    public void setIconImageArray(int[] iconImageArray) {
        this.iconImageArray = iconImageArray;
    }

    public int[] getSelectedIconImageArray() {
        return selectedIconImageArray;
    }

    public void setSelectedIconImageArray(int[] selectedIconImageArray) {
        this.selectedIconImageArray = selectedIconImageArray;
    }
}
