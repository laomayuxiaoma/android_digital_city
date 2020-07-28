package com.muheda.mdsearchview.view;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;

import com.donkingliang.labels.LabelsView;
import com.muheda.mdsearchview.R;
import com.muheda.mdsearchview.adapter.LablesAdapter;
import com.muheda.mdsearchview.databinding.ItemDataBinding;
import com.muheda.mdsearchview.icallback.ICallBack;
import com.muheda.mdsearchview.model.MySearchDto;
import com.muheda.mdsearchview.model.SearchDataDto;
import com.muheda.stateview.BaseView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @author wangfei
 * @date 2019/7/29.
 */
public class HistoryView extends BaseView<SearchDataDto, ItemDataBinding> {
    private ICallBack mCallBack;
    private LablesAdapter mLablesAdapter;

    public HistoryView(Context context, Object data, ViewGroup parent) {
        super(context, data, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_data;
    }

    @Override
    protected void initView(View view, boolean isUpdate) {
        if (data == null) {
            return;
        }
        if (data.getData().size() > 0) {
            mBinding.tvTitle.setText(data.getTitle()+"（" + data.getData().size() + "）");
        }else {
            mBinding.tvTitle.setText(data.getTitle()+"(0)");
        }

        if (mLablesAdapter == null) {
            mLablesAdapter = new LablesAdapter(R.layout.item_lable);
            mBinding.rvData.setLayoutManager(new GridLayoutManager(mBinding.rvData.getContext(), 2));
            mBinding.rvData.setAdapter(mLablesAdapter);
        }
        mLablesAdapter.addList(data.getData());
        mLablesAdapter.setListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(TextView label, Object data, int position) {
                searchAciton((MySearchDto.SearchDto.DataBean) data);
            }
        });
        List<String> list = new ArrayList<>();
        for (MySearchDto.SearchDto.DataBean dataBean:data.getData()){
            list.add(dataBean.getStationName());
        }
        mBinding.lvData.setLabels(list);
        if (null != data.getOnClickListener()) {
            mBinding.ivDelete.setOnClickListener(data.getOnClickListener());
            mBinding.ivDelete.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void initListener(View view, boolean isUpdate) {
        if (data == null) {
            return;
        }
        mCallBack = data.getOnCallBackListener();

        Log.e("TTTTTTTTTTTT", mBinding.lvData.getChildCount() + "|||");
        mBinding.lvData.setLabelBackgroundDrawable(view.getContext().getResources().getDrawable(R.drawable.search_shape_bg_f7f7f7_4));
        mBinding.lvData.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(TextView label, Object data, int position) {
                if (mCallBack!=null){
                    mCallBack.SearchAciton((MySearchDto.SearchDto.DataBean) data);
                    return;
                }
                searchAciton((MySearchDto.SearchDto.DataBean) data);
            }
        });
    }

    @Override
    protected ConbinationBuilder combinationViewBuilder() {
        return null;
    }

    @Override
    protected void initViewConfigure(HashMap viewConfigure) {

    }
}
