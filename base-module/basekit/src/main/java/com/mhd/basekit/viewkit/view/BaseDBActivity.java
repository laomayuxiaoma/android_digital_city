package com.mhd.basekit.viewkit.view;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

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

public abstract class BaseDBActivity<VB extends ViewDataBinding> extends BaseActivity implements ReplaceInterface {

    public static final int WHITE = 1;
    public static final int grey = 2;

    protected VB mBinding;

    protected Map<String, Class> stateMapConfig;

    protected abstract int getLayoutDBId();

    protected abstract void initDBView();

    protected abstract void initDB();

    protected abstract void initMvp(Bundle savedInstanceState);

    protected abstract void initConfig();

    protected abstract void replaceDBLoad();

    private boolean isShow = true;//决策是否展示 主要用于列表首次进入展示 刷新的时候不展示
    private int type = WHITE;
    private StateView ll_load_page;
    protected CustomTitleView base_title;

    private DialogShowUtil dialogUtil = new DialogShowUtil();
    private BaseDialogShow mDialogShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        View rootView = getLayoutInflater().inflate(this.getLayoutDBId(), null, false);
        mBinding = DataBindingUtil.bind(rootView);
        super.setContentView(rootView);
        ll_load_page = (StateView) findViewById(R.id.ll_load_page);
        base_title = (CustomTitleView) findViewById(R.id.base_title);
        if (base_title != null){
            base_title.setTitleColor(getResources().getColor(R.color.color_22262E));
        }
        initColor();
        initBaeeTitle();
        initStateMapConfig();
        initMvp(savedInstanceState);
        initConfig();
        initDBView();
        initDB();
    }

    private void initBaeeTitle() {
        View decorView = getWindow().getDecorView();
        View root = decorView.findViewById(R.id.layout_root);
        View netView = decorView.findViewById(R.id.ll_load_page);
        if (root != null && netView != null && (root.getBackground() instanceof ColorDrawable) && ((ColorDrawable) root.getBackground()).getColor() == getResources().getColor(R.color.color_f7f7f7)) {
            netView.setBackground(root.getBackground());
        }

//        base_title.post(new Runnable() {
//            @Override
//            public void run() {
//                int top = base_title.getTop();
//                if (top > 0) {
//                    base_title.setTop(LightStatusBarUtils.getStatusBarHeight(BaseDBActivity.this));
//                }
//            }
//        });
    }

    protected void initColor() {
        Context context = this;
        if (context instanceof Activity) {
            View decorView = ((Activity) context).getWindow().getDecorView();
            View root = decorView.findViewById(R.id.layout_root);
            if (root != null && root.getBackground() != null) {
                ll_load_page.setBackground(root.getBackground());
            }
        }
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
        if (base_title != null) {
            base_title.setCustomTitle(titleView);
        }
    }


    protected void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }

    protected boolean isShow() {
        return isShow;
    }

    protected void showLoading() {
        if (isShow() && mDialogShow != null && !mDialogShow.isShowing()) {
            mDialogShow.show(this);
        }
    }

    protected void showLoading(boolean isForce) {
        if (isForce && mDialogShow != null && !mDialogShow.isShowing()) {
            mDialogShow.show(this);
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

    @Override
    public void replace() {
        replaceDBLoad();
    }

    protected void setType(int type) {
        this.type = type;
    }
}
