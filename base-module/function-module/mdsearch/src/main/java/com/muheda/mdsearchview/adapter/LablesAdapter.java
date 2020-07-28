package com.muheda.mdsearchview.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.donkingliang.labels.LabelsView;
import com.muheda.mdsearchview.R;
import com.muheda.mdsearchview.model.MySearchDto;

import java.util.List;

/**
 * @author wangfei
 * @date 2019/7/8.
 */
public class LablesAdapter extends BaseRecyclerAdapter<MySearchDto.SearchDto.DataBean, LablesAdapter.ViewHolder> {

    private LabelsView.OnLabelClickListener listener;

    public LablesAdapter(int layout) {
        super(null, layout);
    }

    @Override
    protected LablesAdapter.ViewHolder createMHDViewHolder(Context mContext, View itemView, int viewType) {
        return new ViewHolder(itemView, viewType + "");
    }

    @Override
    protected void bindDate(LablesAdapter.ViewHolder holder, MySearchDto.SearchDto.DataBean dataBean, int position) {
        holder.tv_lable.setText(dataBean.getStationName());
    }

    @Override
    protected void itemClick(Context context, MySearchDto.SearchDto.DataBean dataBean) {
        if (listener != null) {
            listener.onLabelClick(null, dataBean, 0);
        }
    }

    public void setListener(LabelsView.OnLabelClickListener listener) {
        this.listener = listener;
    }

    public List getList() {
        return list;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_lable;

        public ViewHolder(View itemView, String viewType) {
            super(itemView);
            initView(itemView, viewType);
        }

        private void initView(View itemView, String viewType) {
            tv_lable = itemView.findViewById(R.id.tv_lable);
        }
    }
}
