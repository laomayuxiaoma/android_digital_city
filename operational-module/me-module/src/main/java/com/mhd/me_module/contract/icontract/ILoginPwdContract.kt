package com.mhd.me_module.contract.icontract

import com.mhd.basekit.config.IBaseView

interface ILoginPwdContract {
    interface View : IBaseView<Any> {
        //自定义回调
//        fun onLoginResult(loginDto: LoginDto)
//        fun onMessage(message:String)
    }

    interface Presenter {
        //修改密码请求
        fun changePassword(mobile: String, code: String)
    }
}