package com.mhd.deal_module.dispose.meaction

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import android.view.View
import com.mhd.basekit.viewkit.util.Common
import com.mhd.basekit.viewkit.util.route.RouteConstant
import com.mhd.basekit.viewkit.util.route.RouteUtil
import com.mhd.basekit.viewkit.view.webview.LoadingWebViewControl
import com.mhd.basekit.viewkit.view.webview.MHDWebViewActivity
import com.muheda.mhdsystemkit.sytemUtil.functionutil.IntentToActivity

var agreementWebViewSkip = { activity: Activity, title: String, url: String ->
    IntentToActivity.skipActivity(
        activity,
        MHDWebViewActivity::class.java,
        arrayOf(arrayOf<Any>("title", title), arrayOf<Any>("url", url), arrayOf<Any>(MHDWebViewActivity.WEBVIWCONTROL, LoadingWebViewControl::class.java.name))
    )
}

fun viewClick(onClick: View.OnClickListener, vararg views: View) {

    for (view in views) {
        view.setOnClickListener(onClick)
    }
}

fun viewProxyClick(onClick: View.OnClickListener, vararg views: View) {

    for (view in views) {
        view.setOnClickListener(ProxyClickToLogin(onClick))
    }
}

class ProxyClickToLogin(viewClick: View.OnClickListener) : View.OnClickListener {

    var viewClick:View.OnClickListener?=null
    init {
        this.viewClick=viewClick
    }
    override fun onClick(p0: View?) {
        //pass无需校验
        p0.let {
            checkTag(it?.tag=="pass",{
                viewClick?.onClick(p0)
            },{
                checkToken(!TextUtils.isEmpty(Common.getToken()),{
                    viewClick?.onClick(p0)
                },{
                    RouteUtil.routeSkip(RouteConstant.me_loginActivity, arrayOf(),Intent.FLAG_ACTIVITY_NEW_TASK)
                })
            })
        }
    }

    var checkTag = { isPass: Boolean, passFun: () -> Unit, checkFun: () -> Unit ->
        when (isPass) {
            true -> passFun()
            false -> checkFun()
        }

    }

    var checkToken={havaToken:Boolean,toDestination:()->Unit,toLogin:()->Unit ->

        when(havaToken){
            true ->toDestination()
            false->toLogin()
        }
    }


}