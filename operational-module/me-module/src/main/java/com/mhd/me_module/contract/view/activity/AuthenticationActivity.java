package com.mhd.me_module.contract.view.activity;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.immersionbar.ImmersionBar;
import com.mhd.basekit.viewkit.mvp.view.BaseMvpActivity;
import com.mhd.basekit.viewkit.util.route.RouteConstant;
import com.mhd.me_module.R;
import com.mhd.me_module.contract.model.MeConfig;
import com.mhd.me_module.contract.presenter.AuthenticationPresenterImpl;
import com.mhd.me_module.contract.view.assembly.AuthenticatingView;
import com.mhd.me_module.contract.view.assembly.AuthenticationView;
import com.mhd.me_module.databinding.ActivityAuthenticationBinding;

/**
 * 收款账户
 */
@Route(path = RouteConstant.me_authenticationActivity)
public class AuthenticationActivity extends BaseMvpActivity<AuthenticationPresenterImpl, MeConfig, ActivityAuthenticationBinding> implements
        View.OnClickListener {

    private final String VIEW_AUTHER = "VIEW_AUTHER";
    private final String VIEW_AUTHENTICATING = "VIEW_AUTHENTICATING";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_authentication;
    }

    @Override
    protected AuthenticationPresenterImpl creatPresenter() {
        return new AuthenticationPresenterImpl();
    }

    @Override
    protected MeConfig creatConfig() {
        //参数校验类，暂时不需要，我的模块中统一写成MeConfig()
        return new MeConfig();
    }

    @Override
    protected void initView() {
        //初始化view
        setTitle("收款账户");
        //设置view
        mBinding.svView.setData(VIEW_AUTHER,stateMapConfig);
        //设置标题左侧的图片
//        base_title.setLeftImage()
        //设置点击监听
//        viewProxyClick(this, mBinding.tvRegister);
//        mBinding.tvRegister.setOnClickListener(this);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void replaceLoad() {

    }

    @Override
    protected void initStateMapConfig() {
        super.initStateMapConfig();
        stateMapConfig.put(VIEW_AUTHER, AuthenticationView.class);//认证
        stateMapConfig.put(VIEW_AUTHENTICATING, AuthenticatingView.class);//认证中及使用中
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
//        if (id == R.id.tvRegister){//跳转注册页面
//            IntentToActivity.skipActivity(this, RegisterActivity.class);
//        }
    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor(R.color.white).statusBarDarkFont(true).init();
    }
}
