package com.mhd.me_module.contract.view.activity;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.immersionbar.ImmersionBar;
import com.mhd.basekit.viewkit.mvp.view.BaseMvpActivity;
import com.mhd.basekit.viewkit.util.route.RouteConstant;
import com.mhd.me_module.R;
import com.mhd.me_module.contract.model.MeConfig;
import com.mhd.me_module.contract.presenter.UserinfoPresenterImpl;
import com.mhd.me_module.databinding.ActivityUserinfoBinding;
import com.muheda.mhdsystemkit.sytemUtil.functionutil.IntentToActivity;


/**
 * 基本资料页面
 */
@Route(path = RouteConstant.me_userinfoActivity)
public class UserinfoActivity extends BaseMvpActivity<UserinfoPresenterImpl, MeConfig, ActivityUserinfoBinding> implements
        View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_userinfo;
    }

    @Override
    protected UserinfoPresenterImpl creatPresenter() {
        return new UserinfoPresenterImpl();
    }

    @Override
    protected MeConfig creatConfig() {
        return new MeConfig();
    }

    @Override
    protected void initView() {
        //初始化view
        setTitle("基本资料");
        //设置标题左侧的图片
//        base_title.setLeftImage()
        mBinding.llName.setOnClickListener(this);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void replaceLoad() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.llName) {
            IntentToActivity.skipActivity(this, ChangeNameActivity.class);
        }
    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor(R.color.white).statusBarDarkFont(true).init();
    }
}
