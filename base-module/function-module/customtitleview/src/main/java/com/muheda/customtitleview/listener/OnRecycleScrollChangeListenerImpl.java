package com.muheda.customtitleview.listener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muheda.customtitleview.CustomTitleView;

public class OnRecycleScrollChangeListenerImpl implements OnRecycleScrollChangeListener {

    private RecyclerView.OnScrollListener listener;

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        if (listener != null) {
            listener.onScrollStateChanged(recyclerView, newState);
        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        if (listener != null) {
            listener.onScrolled(recyclerView, dx, dy);
        }
    }

    public void attachView(RecyclerView view, final CustomTitleView titleView) {
        attachView(view, titleView, null);
    }

    public void attachView(RecyclerView view, final CustomTitleView titleView, RecyclerView.OnScrollListener listener) {
        this.listener = listener;
        view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                OnRecycleScrollChangeListenerImpl.this.onScrollStateChanged(recyclerView, newState);
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                OnRecycleScrollChangeListenerImpl.this.onScrolled(recyclerView, dx, dy);
                titleView.changTitleAlphaWithDis(dy, true);
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}
