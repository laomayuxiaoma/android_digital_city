package com.mhd.basekit.viewkit.mvp.view;

import android.os.Bundle;

import androidx.databinding.ViewDataBinding;

import com.mhd.basekit.config.BaseConfig;
import com.mhd.basekit.viewkit.mvp.contract.IMvpPresenter;
import com.mhd.basekit.viewkit.mvp.contract.IMvpView;
import com.mhd.basekit.viewkit.view.BaseDBActivity;

import org.greenrobot.eventbus.EventBus;

import Tool.ToastUtils;

/**
 * Created by 13660 on 2018/11/2.
 */

public abstract class BaseMvpActivity<T extends IMvpPresenter, R extends BaseConfig, VB extends ViewDataBinding> extends BaseDBActivity<VB> implements IMvpView {

    protected T presenter;
    protected R config;

    protected abstract int getLayoutId();

    protected abstract T creatPresenter();

    protected abstract R creatConfig();

    protected abstract void initView();

    protected abstract void init();

    protected abstract void replaceLoad();


    @Override
    protected int getLayoutDBId() {
        return getLayoutId();
    }

    @Override
    protected void initMvp(Bundle savedInstanceState) {
        presenter = creatPresenter();
        if (presenter == null) {
            throw new NullPointerException("Presenter is null! Do you return null in createPresenter()?\"");
        }
        presenter.onMvpAttachView(this, savedInstanceState);
    }

    @Override
    protected void initConfig() {
        config = creatConfig();
        if (config == null) {
            throw new NullPointerException("Config is null! Do you return null in creatConfig()?\"");
        }
    }

    @Override
    protected void initDBView() {
        initView();
    }

    @Override
    protected void initDB() {
        init();
    }

    @Override
    protected void replaceDBLoad() {
        replaceLoad();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.onMvpStart();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.onMvpResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (presenter != null) {
            presenter.onMvpPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.onMvpStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onMvpDestory();
            presenter = null;
        }
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showProgressDialog() {
        showLoading();
    }

    @Override
    public void hideProgressDialog(int type) {
        dismiss(type);
    }

    @Override
    public void toastMessage(String message) {
        ToastUtils.showShort(this, message);
    }

}
