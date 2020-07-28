package com.mhd.me_module.contract.view.activity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.immersionbar.ImmersionBar;
import com.mhd.basekit.viewkit.util.route.RouteConstant;
import com.mhd.basekit.viewkit.view.BaseDBActivity;
import com.mhd.basekit.viewkit.view.webview.LoadingWebViewControl;
import com.mhd.basekit.viewkit.view.webview.MHDWebViewActivity;
import com.mhd.me_module.R;
import com.mhd.me_module.databinding.ActivitySettingBinding;
import com.muheda.mhdsystemkit.sytemUtil.functionutil.IntentToActivity;

/**
 * 设置页面
 */
@Route(path = RouteConstant.me_settingActivity)
public class SettingActivity extends BaseDBActivity<ActivitySettingBinding> implements View.OnClickListener {

    @Override
    protected int getLayoutDBId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initDBView() {
        //初始化view
        setTitle("设置");

        mBinding.llDealPwd.setOnClickListener(this);
        mBinding.llLoginPwd.setOnClickListener(this);
    }

    @Override
    protected void initDB() {
        //初始化数据

    }

    @Override
    protected void initMvp(Bundle savedInstanceState) {

    }

    @Override
    protected void initConfig() {

    }

    @Override
    protected void replaceDBLoad() {
        //网络错误时重新加载数据时调用

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.llLoginPwd) {//登录密码修改
            IntentToActivity.skipActivity(this, LogInPwdActivity.class);
        } else if (id == R.id.llDealPwd) {//交易密码修改
            IntentToActivity.skipActivity(this, DealPwdActivity.class);
        } else if (id == R.id.llService) {//服务协议
            IntentToActivity.skipActivity(
                    this,
                    MHDWebViewActivity.class,
                    new Object[][]{{"title", "服务协议"}, {"url", "服务地址"}, {MHDWebViewActivity.WEBVIWCONTROL, LoadingWebViewControl.class}}
            );
        } else if (id == R.id.llPrivacy) {//隐私协议
            IntentToActivity.skipActivity(
                    this,
                    MHDWebViewActivity.class,
                    new Object[][]{{"title", "服务协议"}, {"url", "隐私协议"}, {MHDWebViewActivity.WEBVIWCONTROL, LoadingWebViewControl.class}}
            );
        } else if (id == R.id.tvLogOut) {//退出

        }
    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this).fitsSystemWindows(true).statusBarDarkFont(true).statusBarColor(R.color.white).init();
    }

}
