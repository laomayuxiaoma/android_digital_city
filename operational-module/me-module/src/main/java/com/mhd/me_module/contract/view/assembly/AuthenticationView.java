package com.mhd.me_module.contract.view.assembly;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mhd.me_module.R;
import com.mhd.me_module.databinding.ViewAuthenticationBinding;
import com.muheda.mhdsystemkit.systemUI.stateView.BaseView;

import java.util.HashMap;

/**
 * 收款账户认证页面
 */
public class AuthenticationView extends BaseView<Object, ViewAuthenticationBinding> {


    public AuthenticationView(Context context, Object data, ViewGroup parent) {
        super(context, data, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.view_authentication;
    }

    @Override
    protected void initView(View view, boolean isUpdate) {
        if (!isUpdate) {
//            ((TextView) view.findViewById(R.id.tv_response)).setText("暂无数据");
        }
    }

    @Override
    protected void initListener(View view, boolean isUpdate) {

    }

    @Override
    protected void initViewConfigure(HashMap<String, Class> viewConfigure) {

    }

    @Override
    protected ConbinationBuilder combinationViewBuilder() {
        return null;
    }
}
