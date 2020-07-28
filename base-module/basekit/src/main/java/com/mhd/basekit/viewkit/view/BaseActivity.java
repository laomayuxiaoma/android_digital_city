package com.mhd.basekit.viewkit.view;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.gyf.immersionbar.ImmersionBar;
import com.mhd.basekit.viewkit.swipe.SwipUtil;
import com.mhd.basekit.viewkit.swipe.SwipeBackActivityBase;
import com.mhd.basekit.viewkit.swipe.SwipeBackActivityHelper;
import com.mhd.basekit.viewkit.swipe.SwipeBackLayout;
import com.mhd.basekit.viewkit.util.Common;
import com.umeng.message.PushAgent;


/**
 * Created by 13660 on 2018/10/22.
 */

public class BaseActivity extends FragmentActivity implements SwipeBackActivityBase {
    private SwipeBackActivityHelper mHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Common.activity = this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initImmersionBar();
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
        PushAgent.getInstance(this).onAppStart();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);

    }

    @Override
    public void scrollToFinishActivity() {
        SwipUtil.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }

    @Override
    public Resources getResources() {
        Resources resources = super.getResources();
        if (resources != null && resources.getConfiguration().fontScale != 1.0f) {
            android.content.res.Configuration configuration = resources.getConfiguration();
            configuration.fontScale = 1.0f;
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }
        return resources;
    }

    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected void initImmersionBar() {
        //设置共同沉浸式样式
        ImmersionBar.with(this).fitsSystemWindows(true).statusBarDarkFont(true).init();
    }
}
