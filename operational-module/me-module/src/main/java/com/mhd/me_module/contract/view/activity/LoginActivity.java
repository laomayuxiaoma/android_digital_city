package com.mhd.me_module.contract.view.activity;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.immersionbar.ImmersionBar;
import com.mhd.basekit.viewkit.mvp.view.BaseMvpActivity;
import com.mhd.basekit.viewkit.util.route.RouteConstant;
import com.mhd.me_module.R;
import com.mhd.me_module.contract.model.MeConfig;
import com.mhd.me_module.contract.presenter.LoginPresenterImpl;
import com.mhd.me_module.databinding.ActivityLoginBinding;
import com.muheda.mhdsystemkit.sytemUtil.functionutil.IntentToActivity;

/**
 * 登录页面
 */
@Route(path = RouteConstant.me_loginActivity)
public class LoginActivity extends BaseMvpActivity<LoginPresenterImpl, MeConfig, ActivityLoginBinding> implements
        View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenterImpl creatPresenter() {
        return new LoginPresenterImpl();
    }

    @Override
    protected MeConfig creatConfig() {
        //参数校验类，暂时不需要，我的模块中统一写成MeConfig()
        return new MeConfig();
    }

    @Override
    protected void initView() {
        //初始化view
        setTitle("");
        base_title.enableBottomLineShow(false);
        //设置标题左侧的图片
//        base_title.setLeftImage()
        //设置点击监听
//        viewProxyClick(this, mBinding.tvRegister);
        mBinding.tvRegister.setOnClickListener(this);
        mBinding.tvFindPwd.setOnClickListener(this);
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
        if (id == R.id.tvRegister) {//跳转注册页面
            IntentToActivity.skipActivity(this, RegisterActivity.class);
        } else if (id == R.id.tvFindPwd) {//跳转找回密码页面
            IntentToActivity.skipActivity(this, FindPwdActivity.class);
        }
    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor(R.color.white).statusBarDarkFont(true).init();
    }
}
