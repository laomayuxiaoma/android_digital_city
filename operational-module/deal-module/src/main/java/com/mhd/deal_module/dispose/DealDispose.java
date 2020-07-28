package com.mhd.deal_module.dispose;

import com.example.muheda.functionkit.netkit.params.BaseParams;
import com.mhd.basekit.model.Login.LoginDto;
import com.mhd.basekit.model.net.NengYuanResultDto;
import com.mhd.basekit.viewkit.util.wholeCommon.UrlConstant;

/**
 *   推荐module地址管理
 *
 * @author zhangming
 * @date 2019/6/17.
 */
public enum DealDispose {

    //    LOGIN(UrlConstant.HTTP_URL + UrlConstant.RECOMMEND_FILTER, UserBean.class);
    ME_LOGIN(UrlConstant.ME_LOGIN, LoginDto.class);


    String url;//域名
    Class modelClass; //实体Class
    BaseParams baseParams;//参数校验父类
    boolean isAuto = true;//是否自动解析json 默认是
//    Class mRules = GunYunOriginalResultDto.class;//请求结果拦截
    Class mRules = NengYuanResultDto.class;//请求结果拦截处理
    Object requestJsonParams = null;//请求结果拦截

    /**
     * 默认自动解析 modelClass为null 则返回json
     *
     * @param mUrl
     * @param modelClass
     */
    DealDispose(String mUrl, Class modelClass) {
        this.url = mUrl;
        this.modelClass = modelClass;
    }

    /**
     * @param mUrl
     * @param modelClass
     * @param isAuto     false为手动解析
     */
    DealDispose(String mUrl, Class modelClass, boolean isAuto) {
        this.url = mUrl;
        this.modelClass = modelClass;
        this.isAuto = isAuto;
    }

    /**
     * 增加参数校验
     *
     * @param mUrl
     * @param modelClass
     * @param baseParams 为null 不校验参数
     * @param isAuto
     */
    DealDispose(String mUrl, Class modelClass, BaseParams baseParams, boolean isAuto) {
        this.url = mUrl;
        this.modelClass = modelClass;
        this.baseParams = baseParams;
        this.isAuto = isAuto;
    }
}
