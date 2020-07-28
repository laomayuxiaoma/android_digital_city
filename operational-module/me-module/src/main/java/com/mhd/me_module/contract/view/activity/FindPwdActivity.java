package com.mhd.me_module.contract.view.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.immersionbar.ImmersionBar;
import com.mhd.basekit.viewkit.mvp.view.BaseMvpActivity;
import com.mhd.basekit.viewkit.util.route.RouteConstant;
import com.mhd.me_module.R;
import com.mhd.me_module.contract.model.MeConfig;
import com.mhd.me_module.contract.presenter.FindPwdPresenterImpl;
import com.mhd.me_module.databinding.ActivityFindPwdBinding;

/**
 * 找回密码页面
 */
@Route(path = RouteConstant.me_findPwdActivity)
public class FindPwdActivity extends BaseMvpActivity<FindPwdPresenterImpl, MeConfig, ActivityFindPwdBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_find_pwd;
    }

    @Override
    protected FindPwdPresenterImpl creatPresenter() {
        return new FindPwdPresenterImpl();
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
//        mBinding.wvTest.loadUrl("https://blog.csdn.net/tangedegushi/article/details/81949331");
//        base_title.setLeftListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mBinding.wvTest.loadUrl("https://www.jianshu.com/p/bd0a7c0a52fc");
//                mBinding.wvTest.loadUrl( "javascript:window.location.reload(true)" );
//            }
//        });

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
