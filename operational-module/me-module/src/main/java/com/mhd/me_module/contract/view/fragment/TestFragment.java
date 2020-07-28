package com.mhd.me_module.contract.view.fragment;

import com.mhd.basekit.viewkit.mvp.view.BaseMvpFragment;
import com.mhd.me_module.R;
import com.mhd.me_module.contract.model.MeConfig;
import com.mhd.me_module.contract.presenter.LoginPresenterImpl;
import com.mhd.me_module.databinding.ActivityLoginBinding;

/**
 *
 */
public class TestFragment extends BaseMvpFragment<LoginPresenterImpl, MeConfig, ActivityLoginBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenterImpl creatPresenter() {
        return null;
    }

    @Override
    protected MeConfig creatConfig() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void init() {

    }

    @Override
    protected void replaceLoad() {

    }
}
