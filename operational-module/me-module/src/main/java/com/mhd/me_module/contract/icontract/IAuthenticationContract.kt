package com.mhd.me_module.contract.icontract

import com.mhd.basekit.config.IBaseView
import com.mhd.basekit.model.Login.LoginDto

interface IAuthenticationContract {
    interface View : IBaseView<Any> {
        fun onLoginResult(loginDto: LoginDto)
        fun onMessage(message:String)
    }

    interface Presenter {
        fun auther(mobile: String, code: String)
    }
}