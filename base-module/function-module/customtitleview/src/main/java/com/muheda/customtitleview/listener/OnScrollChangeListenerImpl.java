package com.muheda.customtitleview.listener;

import androidx.core.widget.NestedScrollView;

import com.muheda.customtitleview.CustomTitleView;


public class OnScrollChangeListenerImpl implements OnNestedScrollChangeListener {

    private NestedScrollView.OnScrollChangeListener listener;

    @Override
    public void onScrollChange(NestedScrollView var1, int var2, int var3, int var4, int var5) {
        if (listener != null) {
            listener.onScrollChange(var1, var2, var3, var4, var5);
        }
    }

    public void attachView(NestedScrollView view, final CustomTitleView titleView) {
        attachView(view, titleView, null);
    }

    public void attachView(NestedScrollView view, final CustomTitleView titleView, NestedScrollView.OnScrollChangeListener listener) {
            this.listener = listener;
            view.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
                    OnScrollChangeListenerImpl.this.onScrollChange(nestedScrollView, i, i1, i2, i3);
                    titleView.changTitleAlphaWithDis(i1, false);
                }
            });
    }
}
