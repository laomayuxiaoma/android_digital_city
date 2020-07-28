package com.muheda.mhdsystemkit.systemUI.conView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.muheda.mhdsystemkit.sytemUtil.uiutil.SizeUtils;

/**
 * Created by 13660 on 2019/4/22.
 */

public abstract class BaseModelView<VB extends ViewDataBinding>{
    protected VB mBinding;
    public DataModel data;
    private View root;
    public abstract int creatLayout();
    public abstract void initView();
    public abstract void init();
    public BaseModelView(Context context, ViewGroup viewGroup) {

        root= LayoutInflater.from(context).inflate(creatLayout(),viewGroup,false);
        mBinding= DataBindingUtil.bind(root);
        initView();

    }

    public View getView(){
        return root;
    }

    public void setData(DataModel data){
        this.data=data;
    }

    public void setMarign(int top,int bottom){
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, SizeUtils.dp2px(top),0,SizeUtils.dp2px(bottom));
        root.setLayoutParams(layoutParams);
    }
}
