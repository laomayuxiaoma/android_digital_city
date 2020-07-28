package com.mhd.me_module.contract.presenter

import com.mhd.basekit.viewkit.mvp.presenter.BasePresenter
import com.mhd.me_module.contract.icontract.IDealPwdContract
import io.reactivex.disposables.Disposable

class DealPwdPresenterImpl : BasePresenter<IDealPwdContract.View>(), IDealPwdContract.Presenter {

    private var meDisposable: Disposable? = null

    override fun changeDealPassword(mobile: String, code: String) {
        //配置请求参数
//        val httpNewParams = HttpParamsUtil.getCommonRequestParams(arrayOf(arrayOf<Any>("mobile", mobile), arrayOf<Any>("code", code)))
//        //发起请求
//        meDisposable = MHDHttp.post(MeDispose.ME_LOGIN, httpNewParams, object : OnNewListener<LoginDto> {
//            override fun onSuccess(t: LoginDto?) {
//                //请求成功回调
////                view.onLoginResult(t!!)
//            }
//
//            override fun onErrorOrExpection() {
//                //请求报错时回调
//
//            }
//
//            override fun onOtherState(code: String?, message: String?) {
//                //请求返回的其他状态
////                view.onMessage(message!!)
//            }
//
//            override fun onNull(code: String?, message: String?) {
//                //数据为null回调
////                view.onMessage(message!!)
//            }
//        })
    }
}