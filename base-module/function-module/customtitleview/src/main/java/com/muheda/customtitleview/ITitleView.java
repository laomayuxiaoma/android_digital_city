package com.muheda.customtitleview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 当使用自定义title时,实现此类即可(包括左中右下的布局都要继承此类)
 */

public abstract class ITitleView {

    private View root;

    public ITitleView(Context context){
        if (context instanceof Activity){
            root = LayoutInflater.from(context).inflate(getLayoutId(), (ViewGroup) ((Activity)context).getWindow().getDecorView(),false);
        }else {
            root = LayoutInflater.from(context).inflate(getLayoutId(),null,false);
        }
        initView(root);
        initListener(root);
    }

    public abstract int getLayoutId();

    protected abstract void initView(View view);

    protected abstract void initListener(View view);

    public View getView(){
        return root;
    }
}
