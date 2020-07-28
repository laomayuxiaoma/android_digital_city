package com.mhd.me_module.contract.view.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.immersionbar.ImmersionBar;
import com.mhd.basekit.viewkit.mvp.view.BaseMvpActivity;
import com.mhd.basekit.viewkit.util.route.RouteConstant;
import com.mhd.me_module.R;
import com.mhd.me_module.contract.model.MeConfig;
import com.mhd.me_module.contract.presenter.LoginPwdPresenterImpl;
import com.mhd.me_module.databinding.ActivityLoginPwdBinding;

/**
 * 登录密码修改页面
 */
@Route(path = RouteConstant.me_loginPwdActivity)
public class LogInPwdActivity extends BaseMvpActivity<LoginPwdPresenterImpl, MeConfig, ActivityLoginPwdBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_pwd;
    }

    @Override
    protected LoginPwdPresenterImpl creatPresenter() {
        return new LoginPwdPresenterImpl();
    }

    @Override
    protected MeConfig creatConfig() {
        return new MeConfig();
    }

    @Override
    protected void initView() {
        //初始化view
        setTitle("修改登录密码");
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
