package com.mhd.basekit.adapterkit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.muheda.functionkit.norepeatclickkit.OnSingleClickListener;
import com.example.muheda.functionkit.norepeatclickkit.SingleClickUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13660 on 2018/3/23.
 */

public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected List<T> list = null;
    private int layout;
    protected int duration = 1;

    public BaseRecyclerAdapter(List<T> list, int layout) {
        this.list = list;
        this.layout = layout;
    }

    protected abstract VH createMHDViewHolder(Context mContext, View itemView, int viewType);

    @Override
    public VH onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        final VH recyclerViewHolder;
        VH holder = createMHDViewHolder(parent.getContext(), view, viewType);
        if (holder == null) {
            recyclerViewHolder = (VH) new BaseRecyclerViewHolder(parent.getContext(), view);
        } else {
            recyclerViewHolder = holder;
        }
        SingleClickUtil.setOnSingleClickListener(view, duration, new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                if (recyclerViewHolder.getAdapterPosition() < list.size()) {
                    itemClick(parent.getContext(), list.get(recyclerViewHolder.getAdapterPosition()));
                } else {
                    itemClick(parent.getContext(), null);
                }
            }
        });

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (position < list.size()) {
            bindDate(holder, list.get(position), position);
        } else {
            bindDate(holder, null, position);
        }
    }

    protected abstract void bindDate(VH holder, T t, int position);

    protected abstract void itemClick(Context context, T t);

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void addList(ArrayList<T> addList) {
        this.list = addList;
        notifyDataSetChanged();
    }

    protected List<T> getList() {
        return list;
    }

}
