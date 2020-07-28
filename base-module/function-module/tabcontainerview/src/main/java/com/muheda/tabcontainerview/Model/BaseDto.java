package com.muheda.tabcontainerview.Model;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * @author wangfei
 * @date 2019/8/13.
 */
public class BaseDto {

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Fragment[] getFragmentArray() {
        return fragmentArray;
    }

    public void setFragmentArray(Fragment[] fragmentArray) {
        this.fragmentArray = fragmentArray;
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    private Context context;
    private Fragment[] fragmentArray;
    private FragmentManager fragmentManager;

    public BaseDto(Context context, Fragment[] fragmentArray, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentArray = fragmentArray;
        this.fragmentManager = fragmentManager;
    }
}
