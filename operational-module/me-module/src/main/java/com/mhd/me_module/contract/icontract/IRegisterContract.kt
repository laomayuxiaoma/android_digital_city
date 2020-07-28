package com.mhd.me_module.contract.icontract

import com.mhd.basekit.config.IBaseView
import com.mhd.basekit.model.Login.LoginDto

interface IRegisterContract {
    interface View : IBaseView<Any> {
        fun onLoginResult(loginDto: LoginDto)
        fun onMessage(message:String)
        fun onMsgResult()
    }

    interface Presenter {
        fun sendSms(mobile: String)
        fun register(mobile: String, code: String)
    }
}