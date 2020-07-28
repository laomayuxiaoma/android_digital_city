package com.muheda.tabcontainerview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.ViewPager;

import com.muheda.tabcontainerview.adapter.BaseAdapter;
import com.muheda.tabcontainerview.adapter.TabViewPagerAdapter;
import com.muheda.tabcontainerview.listener.OnTabSelectedListener;
import com.muheda.tabcontainerview.widget.AbsTab;
import com.muheda.tabcontainerview.widget.TabHost;


public class TabContainerView extends RelativeLayout {

    private int preIndex = 0;
    /**
     * 中间ViewPager
     */
    private ViewPager mContentViewPager;

    /**
     * 分割线
     */
    private int mDivideLineColor;
    private int mDivideLineHeight;

    /**
     * 底部TabLayout
     */
    private TabHost mTabHost;

    /**
     * 选中监听
     */
    private OnTabSelectedListener mOnTabSelectedListener;


    public TabContainerView(Context context) {
        super(context);
    }

    public TabContainerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);
    }

    public TabContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TabContainerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(context, attrs);
    }

    /**
     * 初始化UI
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        initStyle(context, attrs);
        initTabHost(context);
        initDivideLine(context);
        initViewPager(context);

        mTabHost.setContentViewPager(mContentViewPager);
    }

    private void initStyle(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabContainerViewStyle);
        mDivideLineColor = typedArray.getColor(R.styleable.TabContainerViewStyle_divideLineColor, Color.BLACK);
        mDivideLineHeight = typedArray.getInt(R.styleable.TabContainerViewStyle_divideLineHeight, 2);

        typedArray.recycle();
    }


    private void initTabHost(Context context) {
        mTabHost = new TabHost(context);
        addView(mTabHost.getRootView());
    }

    private void initDivideLine(Context context) {
        View divideLine = new View(context);
        divideLine.setId(R.id.divide_tab);
        divideLine.setBackgroundColor(mDivideLineColor);

        LayoutParams lineLp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mDivideLineHeight);
        lineLp.addRule(RelativeLayout.ABOVE, R.id.linearlayout_tab);
        divideLine.setLayoutParams(lineLp);

        addView(divideLine);
    }

    private void initViewPager(Context context) {
        mContentViewPager = new ViewPager(context);

        LayoutParams contentVpLp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        contentVpLp.addRule(RelativeLayout.ABOVE, R.id.divide_tab);
        mContentViewPager.setLayoutParams(contentVpLp);
        mContentViewPager.setId(R.id.viewpager_tab);

        mContentViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mTabHost.changeTabHostStatus(position);

                AbsTab selectedTab = mTabHost.getTabForIndex(position);
                if (mOnTabSelectedListener != null && selectedTab != null) {
                    mOnTabSelectedListener.onTabSelected(selectedTab, preIndex);
                }
                preIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        addView(mContentViewPager);
    }

    public TabContainerView setAdapter(BaseAdapter baseAdapter) {
        setAdapter(baseAdapter, 0);
        return this;
    }

    public TabContainerView setAdapter(BaseAdapter baseAdapter, int index) {
        if (baseAdapter == null) return this;

        mTabHost.addTabs(baseAdapter);
        mContentViewPager.setAdapter(new TabViewPagerAdapter(baseAdapter.getFragmentManager(), baseAdapter.getFragmentArray()));

        setCurrentItem(index);

        return this;
    }

    /**
     * 设置当前选中的tab
     *
     * @param index
     */
    public TabContainerView setCurrentItem(int index) {
        mTabHost.changeTabHostStatus(index);
        mContentViewPager.setCurrentItem(index);
        return this;
    }

    /**
     * 显示消息提示
     *
     * @param index
     */
    public TabContainerView setCurrentMessageItem(int index) {
        setCurrentMessageItem(index, -1);
        return this;
    }

    /**
     * 设置消息提示数量
     *
     * @param index
     */
    public TabContainerView setCurrentMessageItem(int index, int count) {
        AbsTab tab = mTabHost.getTabForIndex(index);
        tab.showMessageTip(true, count);
        return this;
    }

    /**
     * 清除红点提示
     *
     * @param index
     */
    public TabContainerView clearMessageItem(int index) {
        AbsTab tab = mTabHost.getTabForIndex(index);
        tab.showMessageTip(false, 1);
        return this;
    }

    /**
     * 设置TabHost背景颜色
     */
    public TabContainerView setTabHostBgColor(int bgColor) {
        mTabHost.setTabBgColor(bgColor);
        return this;
    }

    /**
     * 设置预加载页数
     *
     * @param limit
     */
    public TabContainerView setOffscreenPageLimit(int limit) {
        mContentViewPager.setOffscreenPageLimit(limit);
        return this;
    }

    /**
     * 设置选中tab监听
     *
     * @param onTabSelectedListener
     */
    public TabContainerView setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        this.mOnTabSelectedListener = onTabSelectedListener;
        return this;
    }


}
