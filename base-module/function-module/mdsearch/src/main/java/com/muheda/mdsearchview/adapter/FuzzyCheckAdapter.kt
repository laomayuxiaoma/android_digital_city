package com.muheda.mdsearchview.adapter

import android.content.Context
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.muheda.mdsearchview.icallback.ICallBack
import com.muheda.mdsearchview.model.MySearchDto
import kotlinx.android.synthetic.main.item_fuzzy.view.*

/**
 * @author wangfei
 * @date 2019/7/8.
 */
class FuzzyCheckAdapter(layout: Int, private val mCallBack: ICallBack) :
    BaseRecyclerAdapter<MySearchDto.SearchDto.DataBean, RecyclerView.ViewHolder>(null, layout) {
    override fun bindDate(holder: RecyclerView.ViewHolder, dataBean: MySearchDto.SearchDto.DataBean, position: Int) {
        if (!TextUtils.isEmpty(key) && dataBean.stationName.length >= key!!.length) {
            val str = SpannableString(dataBean.stationName)
            val foregroundColorSpan = ForegroundColorSpan(Color.parseColor("#4385F5"))
            str.setSpan(foregroundColorSpan, 0, key!!.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            holder.itemView.tv_name_search.text = str
        }else{
            holder.itemView.tv_name_search.text = dataBean.stationName
        }
        holder.itemView.tv_address_search.text = dataBean.address
    }

    private var key: String? = null

    override fun createMHDViewHolder(
        mContext: Context,
        itemView: View,
        viewType: Int
    ): ViewHolder? {
        return null
    }

    override fun itemClick(context: Context, dataBean: MySearchDto.SearchDto.DataBean) {
        dataBean.fuzzy = false
        dataBean.item = true
        mCallBack.SearchAciton(dataBean)
    }

    fun setKey(string: String) {
        key = string
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
