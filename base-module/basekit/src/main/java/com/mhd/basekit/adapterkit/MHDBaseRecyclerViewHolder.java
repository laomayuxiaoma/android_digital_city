package com.mhd.basekit.adapterkit;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by 13660 on 2018/3/23.
 */

public abstract class MHDBaseRecyclerViewHolder extends RecyclerView.ViewHolder {
    private View convertView;
    private Context mContext;

    public MHDBaseRecyclerViewHolder(Context mContext, View itemView) {
        super(itemView);
        convertView = itemView;
        this.mContext = mContext;
        initView(mContext, itemView);
    }

    protected abstract void initView(Context mContext, View itemView);

    public <T> T findViewById(int id){
        return (T)itemView.findViewById(id);
    }

}
