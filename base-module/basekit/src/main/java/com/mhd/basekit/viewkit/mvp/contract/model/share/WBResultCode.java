package com.mhd.basekit.viewkit.mvp.contract.model.share;

/**
 * @author zhangming
 * @Date 2019/5/22 17:02
 * @Description: wei
 */
public class WBResultCode {
    public static final int ERR_OK = 0;
    public static final int ERR_CANCEL = 1;
    public static final int ERR_FAIL = 2;

    public WBResultCode(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
