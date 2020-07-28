package com.mhd.basekit.model.net

/**
 * 项目名称：guoyun
 * 包名：com.dashen.guoyun.base
 * 创建人：meiyuan
 * 创建时间：2018/05/05
 * 类描述：所有实体数据父类
 * 备注：
 */
class ResultBean<T> {
    /**
     *参数说明:
     *retcode: 0表示调用成功，非0表示失败
     *retmsg: 调用失败时返回失败的原因
     *data: 返回结果的数据体，依据不同业务返回不同，调用时参照具体api文档
     */
    var retcode: Int = 0
    var retmsg: String? = null
    var data: T? = null

}
