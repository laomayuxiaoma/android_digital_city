package com.muheda.customrefreshlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;

import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.internal.InternalClassics;

public abstract class ICustomFooter extends InternalClassics<ClassicsFooter> implements RefreshFooter {
    public ICustomFooter(Context context) {
        this(context,null);
    }

    public ICustomFooter(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ICustomFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(getLayoutId(),this);
        initView(this);
    }

    protected abstract void initView(View view);

    public abstract int getLayoutId();

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        onCustomFinish(refreshLayout,success);
        return super.onFinish(refreshLayout, success);
    }

    protected abstract void onCustomFinish(RefreshLayout refreshLayout, boolean success);

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        onCustomStateChanged(refreshLayout,oldState,newState);
        super.onStateChanged(refreshLayout, oldState, newState);
    }

    protected abstract void onCustomStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState);

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {
        onCustomMoving(isDragging,percent,offset,height,maxDragHeight);
        super.onMoving(isDragging, percent, offset, height, maxDragHeight);
    }

    protected abstract void onCustomMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight);
}
