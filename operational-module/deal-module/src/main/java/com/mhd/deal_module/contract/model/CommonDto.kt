package com.mhd.deal_module.contract.model

import com.example.muheda.functionkit.netkit.model.ModelDto

/**
 * 创建日期：2019/11/25 on 15:19
 * 描述:  适用于不需要具体返回数据的网络请求
 * 作者: zhangming
 */
class CommonDto : ModelDto() {

    override fun toJsonDto(jsonString: String): ModelDto? {
        return null
    }
}
