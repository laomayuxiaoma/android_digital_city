package com.mhd.android_digital_city.adapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * @author zhangming
 * @Date 2019/11/12 13:35
 * @Description: 首页apdpter
 */
public class HomeFragmentAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragmentList;

    public HomeFragmentAdapter(List<Fragment> mFragmentList, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mFragmentList = mFragmentList;
    }

    @Override
    public Fragment getItem(int itemPosition) {
        return mFragmentList.get(itemPosition);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void updateList(List<Fragment> mFragmentList) {
        this.mFragmentList = mFragmentList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if (mFragmentList.contains(object)) {
            // 如果当前 item 未被 remove，则返回 item 的真实 position
            return mFragmentList.indexOf(object);
        } else {
            // 否则返回状态值 POSITION_NONE
            return POSITION_NONE;
        }
    }

}
