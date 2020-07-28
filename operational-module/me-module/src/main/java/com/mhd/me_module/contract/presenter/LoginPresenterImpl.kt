package com.mhd.me_module.contract.presenter

import com.example.muheda.functionkit.netkit.http.MHDHttp
import com.example.muheda.functionkit.netkit.http.OnNewListener
import com.example.muheda.functionkit.netkit.params.HttpParamsUtil
import com.mhd.basekit.model.Login.LoginDto
import com.mhd.basekit.viewkit.mvp.presenter.BasePresenter
import com.mhd.me_module.contract.icontract.ILoginContract
import com.mhd.me_module.dispose.MeDispose
import io.reactivex.disposables.Disposable

class LoginPresenterImpl : BasePresenter<ILoginContract.View>(), ILoginContract.Presenter {

    private var meDisposable: Disposable? = null

    override fun sendSms(mobile: String) {
//        val httpNewParamsarams = HttpParamsUtil.getCommonRequestParams(arrayOf(arrayOf<Any>("mobile", mobile)))
//        meDisposable = MHDHttp.post(MeDispose.ME_SEND_SMS, httpNewParams, object : OnNewListener<CommonDto> {
//            override fun onSuccess(t: CommonDto?) {
//
//            }
//
//            override fun onErrorOrExpection() {
//
//            }
//
//            override fun onOtherState(code: String?, message: String?) {
//                view.onMessage(message!!)
//            }
//
//            override fun onNull(code: String?, message: String?) {
//                view.onMessage(message!!)
//                //临时
//                view.onMsgResult()
//            }
//        })
    }

    override fun login(mobile: String, code: String) {
        val httpNewParams = HttpParamsUtil.getCommonRequestParams(arrayOf(arrayOf<Any>("mobile", mobile), arrayOf<Any>("code", code)))
        meDisposable = MHDHttp.post(MeDispose.ME_LOGIN, httpNewParams, object : OnNewListener<LoginDto> {
            override fun onSuccess(t: LoginDto?) {
//                Log.e("TTTTTTTTT", "" + t!! + "||")
                view.onLoginResult(t!!)
            }

            override fun onErrorOrExpection() {

            }

            override fun onOtherState(code: String?, message: String?) {
                view.onMessage(message!!)
            }

            override fun onNull(code: String?, message: String?) {
                view.onMessage(message!!)
            }
        })
    }
}