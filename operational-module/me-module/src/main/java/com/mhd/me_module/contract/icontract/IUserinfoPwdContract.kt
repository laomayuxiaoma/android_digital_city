package com.mhd.me_module.contract.icontract

import com.mhd.basekit.config.IBaseView

interface IUserinfoPwdContract {
    interface View : IBaseView<Any> {
        //自定义回调
//        fun onLoginResult(loginDto: LoginDto)
    }

    interface Presenter {
        //获取用户数据请求
        fun getUserinfo(mobile: String, code: String)
    }
}