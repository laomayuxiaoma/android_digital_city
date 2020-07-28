package com.mhd.basekit.viewkit.util.alipay;

/**
 * @author wangfei
 * @date 2019/5/21.
 */
public interface IAliPayResult {
    void success();

    void fail();

    void getPayData(AliPayDto aliPayDto);
}
