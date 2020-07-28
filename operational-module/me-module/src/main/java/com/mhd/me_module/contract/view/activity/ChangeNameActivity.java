package com.mhd.me_module.contract.view.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.immersionbar.ImmersionBar;
import com.mhd.basekit.viewkit.mvp.view.BaseMvpActivity;
import com.mhd.basekit.viewkit.util.route.RouteConstant;
import com.mhd.me_module.R;
import com.mhd.me_module.contract.model.MeConfig;
import com.mhd.me_module.contract.presenter.ChangeNamePresenterImpl;
import com.mhd.me_module.databinding.ActivityChangeNameBinding;


/**
 * 修改昵称页面
 */
@Route(path = RouteConstant.me_changeNameActivity)
public class ChangeNameActivity extends BaseMvpActivity<ChangeNamePresenterImpl, MeConfig, ActivityChangeNameBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_name;
    }

    @Override
    protected ChangeNamePresenterImpl creatPresenter() {
        return new ChangeNamePresenterImpl();
    }

    @Override
    protected MeConfig creatConfig() {
        return new MeConfig();
    }

    @Override
    protected void initView() {
        //初始化view
        setTitle("修改昵称");
        //设置标题左侧的图片
//        base_title.setLeftImage()
    }

    @Override
    protected void init() {

    }

    @Override
    protected void replaceLoad() {

    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor(R.color.white).statusBarDarkFont(true).init();
    }
}
