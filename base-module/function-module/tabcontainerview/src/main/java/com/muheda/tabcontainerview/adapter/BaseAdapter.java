package com.muheda.tabcontainerview.adapter;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.muheda.tabcontainerview.Model.BaseDto;
import com.muheda.tabcontainerview.widget.AbsTab;


public abstract class BaseAdapter {

    private Fragment[] mFragmentArray;
    private FragmentManager mFragmentManager;
    protected Context mContext;

    public BaseAdapter(BaseDto baseDto) {
        mContext = baseDto.getContext();
        mFragmentArray = baseDto.getFragmentArray();
        mFragmentManager = baseDto.getFragmentManager();
    }

    /**
     * tab数量
     */
    public int getCount() {
        return mFragmentArray != null ? mFragmentArray.length : 0;
    }

    /**
     * fragment 数组
     */
    public Fragment[] getFragmentArray() {
        return mFragmentArray;
    }

    public FragmentManager getFragmentManager() {
        return mFragmentManager;
    }

    /**
     * 得到tab
     *
     * @return
     */
    public abstract AbsTab getTab(int index);

}
