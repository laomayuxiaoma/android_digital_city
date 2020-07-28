package com.mhd.me_module.contract.icontract

import com.mhd.basekit.config.IBaseView

interface IDealPwdContract {
    interface View : IBaseView<Any> {
        //自定义回调
//        fun onLoginResult(loginDto: LoginDto)
//        fun onMessage(message:String)
    }

    interface Presenter {
        //修改交易密码请求
        fun changeDealPassword(mobile: String, code: String)
    }
}