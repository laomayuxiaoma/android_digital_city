package com.mhd.basekit.viewkit.mvp.view.netstateview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mhd.basekit.R;
import com.mhd.basekit.databinding.NetNoDataBinding;
import com.muheda.mhdsystemkit.systemUI.stateView.BaseView;

import java.util.HashMap;

/**
 * 暂无数据
 */
public class NoData extends BaseView<Object, NetNoDataBinding> {


    public NoData(Context context, Object data, ViewGroup parent) {
        super(context, data, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.net_no_data;
    }

    @Override
    protected void initView(View view, boolean isUpdate) {
        if (!isUpdate) {
            ((TextView) view.findViewById(R.id.tv_response)).setText("暂无数据");
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
