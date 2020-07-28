package com.muheda.customtitleview.listener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public interface OnRecycleScrollChangeListener extends OnScrollChangeListener{

    void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState);

    void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy);
}
