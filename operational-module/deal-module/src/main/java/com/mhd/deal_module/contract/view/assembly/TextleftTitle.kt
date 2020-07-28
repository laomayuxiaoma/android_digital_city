package com.mhd.deal_module.contract.view.assembly

import android.app.Activity
import android.content.Context
import android.view.View
import com.mhd.deal_module.R
import com.muheda.customtitleview.ITitleView
import com.muheda.mhdsystemkit.sytemUtil.functionutil.IntentToActivity
import kotlinx.android.synthetic.main.deal_left_text_title.view.*

/**
 * 创建日期：2019/11/13 on 11:21
 * 描述: title左侧为文字的布局
 * 作者: zhangming
 */

class TextleftTitle(context: Context?) : ITitleView(context),
    View.OnClickListener {

    var listener: View.OnClickListener? = null

//    init {
//        this.listener = listener
//    }

    override fun getLayoutId(): Int {
        return R.layout.deal_left_text_title
    }

    override fun initView(view: View?) {

    }

    override fun initListener(view: View?) {
        view!!.tvLeft.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.tvLeft -> {
//                IntentToActivity.skipActivity(view.context as Activity?,SettingActivity::class.java)
            }
        }
    }

    fun setImageBackgroundResource(resid:Int){
//        view.imgSetting.setBackgroundResource(resid)
    }

}