package com.mhd.basekit.viewkit.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.SimpleImmersionOwner;
import com.gyf.immersionbar.components.SimpleImmersionProxy;
import com.mhd.basekit.R;
import com.mhd.basekit.viewkit.util.ReplaceInterface;
import com.mhd.basekit.viewkit.view.dialog.BaseDialogShow;
import com.mhd.basekit.viewkit.view.dialog.DialogShowUtil;
import com.mhd.basekit.viewkit.view.dialog.GlobalLoadDialogShow;
import com.muheda.customtitleview.CustomTitleView;
import com.muheda.customtitleview.ITitleView;
import com.muheda.mhdsystemkit.systemUI.stateView.StateView;

import java.util.Map;

/**
 * Created by 13660 on 2018/10/22.
 */

public abstract class BaseDBFragment<VB extends ViewDataBinding> extends Fragment implements SimpleImmersionOwner, ReplaceInterface {

    protected VB mBinding;

    protected Map<String, Class> stateMapConfig;

    protected abstract int getLayoutDBId();

    protected abstract void initMvp(Bundle savedInstanceState);

    protected abstract void initConfig();

    protected abstract void initDBView();

    protected abstract void initDB();

    protected abstract void replaceDBLoad();

    private boolean isShow = true;//决策是否展示 主要用于列表首次进入展示 刷新的时候不展示
    private StateView ll_load_page;
    protected CustomTitleView base_title;

    private DialogShowUtil dialogUtil = new DialogShowUtil();
    private BaseDialogShow mDialogShow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutDBId(), container, false);
        ll_load_page = (StateView) mBinding.getRoot().findViewById(R.id.ll_load_page);
        base_title = (CustomTitleView) mBinding.getRoot().findViewById(R.id.base_title);
        if (base_title != null) {
            base_title.setTitleColor(getResources().getColor(R.color.color_22262E));
        }
        initBaseTitle();
        initStateMapConfig();
        initMvp(savedInstanceState);
        initConfig();
        initDBView();
        initDB();
        return mBinding.getRoot();
    }

    protected void initBaseTitle() {
//        base_title.post(new Runnable() {
//            @Override
//            public void run() {
//                int top = base_title.getTop();
//                if (top > 10) {
//                    base_title.setTop(LightStatusBarUtils.getStatusBarHeight(getActivity()));
//                }
//            }
//        });
    }

    protected void hideLeftBack(boolean isHide) {
        if (base_title != null) {
            base_title.enableLeftShow(isHide);
        }
    }

    protected void setTitle(String title) {
        if (base_title != null) {
            base_title.setTitle(title);
        }
    }

    protected void initStateMapConfig() {
        mDialogShow = ((GlobalLoadDialogShow) dialogUtil.getDialogWithClass(GlobalLoadDialogShow.class)).init(this);
        stateMapConfig = ((GlobalLoadDialogShow) mDialogShow).getMMapConfig();
    }

    public void setDialog(BaseDialogShow dialogShow) {
        mDialogShow = dialogShow;
    }

    public void setDialog(Class aClass) {
        mDialogShow = dialogUtil.getDialogWithClass(aClass);
    }

    /**
     * 自定义左侧布局
     *
     * @param titleView
     */
    protected void setTitleLeftLayout(ITitleView titleView) {
        if (base_title != null) {
            base_title.setTitleLeftLayout(titleView);
        }
    }

    /**
     * 自定义中间布局
     *
     * @param titleView
     */
    protected void setTitleCenterLayout(ITitleView titleView) {
        if (base_title != null) {
            base_title.setTitleCenterLayout(titleView);
        }
    }

    /**
     * 自定义右侧布局
     *
     * @param titleView
     */
    protected void setTitleRightLayout(ITitleView titleView) {
        if (base_title != null) {
            base_title.setTitleRightLayout(titleView);
        }
    }

    /**
     * 顶部全自定义布局
     *
     * @param titleView
     */
    protected void setCustomTitle(ITitleView titleView) {
        base_title.setCustomTitle(titleView);
    }


    protected void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }

    protected boolean isShow() {
        return isShow;
    }


    public void dismiss() {
        if (mDialogShow != null) {
            mDialogShow.dismiss(GlobalLoadDialogShow.NET_STATE_DISMISS);
        }
    }

    protected void showLoading() {
        if (isShow() && mDialogShow != null && !mDialogShow.isShowing()) {
            mDialogShow.show(getActivity());
        }
    }

    protected void showLoading(boolean isForce) {
        if (isForce && mDialogShow != null && !mDialogShow.isShowing()) {
            mDialogShow.show(getActivity());
        }
    }

    public void dismiss(int type) {
        //type 1 成功 2 成功-数据为空 (包括非200) 4 请求失败  网络
        if (isShow() && mDialogShow != null) {
            mDialogShow.dismiss(type);
        }
    }

    protected void dismiss(int type, boolean isForce) {
        //type 1 成功 2 成功-数据为空 (包括非200) 4 请求失败  网络
        if (isForce && mDialogShow != null) {
            mDialogShow.dismiss(type);
        }
    }




    /**
     * ImmersionBar代理类
     */
    private SimpleImmersionProxy mSimpleImmersionProxy = new SimpleImmersionProxy(this);

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mSimpleImmersionProxy.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSimpleImmersionProxy.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSimpleImmersionProxy.onDestroy();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mSimpleImmersionProxy.onHiddenChanged(hidden);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mSimpleImmersionProxy.onConfigurationChanged(newConfig);
    }

    /**
     * 是否可以实现沉浸式，当为true的时候才可以执行initImmersionBar方法
     * Immersion bar enabled boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean immersionBarEnabled() {
        return true;
    }

    @Override
    public void initImmersionBar() {
        //设置共同沉浸式样式
        ImmersionBar.with(this).fitsSystemWindows(true).statusBarDarkFont(true).init();
    }

    public VB getmBinding() {
        return mBinding;
    }
}
