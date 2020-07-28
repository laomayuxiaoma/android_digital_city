package com.mhd.deal_module.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mhd.basekit.adapterkit.BaseRecyclerAdapter
import com.mhd.deal_module.contract.model.DealHomeDto

/**
 * @author wangfei
 * @Date 2019/11/27
 * @Description: 交易首页的adapter
 */
class DealHomeAdapter(layout: Int) :
        BaseRecyclerAdapter<DealHomeDto, DealHomeAdapter.ViewHolder>(null, layout) {

    override fun createMHDViewHolder(mContext: Context, itemView: View, viewType: Int): ViewHolder {
        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun bindDate(holder: ViewHolder, tagDto: DealHomeDto, position: Int) {

//        holder.itemView.tv_time.text = tagDto.startTime + "-" + tagDto.endTime
//        holder.itemView.tv_fee.text =
//            "电费：" + tagDto.electricFee + "元/度   " + "服务费：" + tagDto.serviceFee + "元/度"
//        holder.itemView.tv_total_fee.text = tagDto.totalFee
    }

    override fun itemClick(context: Context?, t: DealHomeDto?) {

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
