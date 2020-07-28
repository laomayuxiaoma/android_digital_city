package com.mhd.basekit.viewkit.util.alipay;

import com.example.muheda.functionkit.netkit.model.ModelDto;

/**
 * @author wangfei
 * @date 2019/5/21.
 */
public class AliPayDto extends ModelDto {
    @Override
    public ModelDto toJsonDto(String jsonString) {
        return null;
    }


    public AliPayDataBean getData() {
        return data;
    }

    public void setData(AliPayDataBean data) {
        this.data = data;
    }

    private AliPayDataBean data;

    public class AliPayDataBean {

        public String getMhdTradeNo() {
            return mhdTradeNo;
        }

        public void setMhdTradeNo(String mhdTradeNo) {
            this.mhdTradeNo = mhdTradeNo;
        }

        public String getPayInfo() {
            return payInfo;
        }

        public void setPayInfo(String payInfo) {
            this.payInfo = payInfo;
        }

        public Integer getOrderId() {
            return orderId;
        }

        public void setOrderId(Integer orderId) {
            this.orderId = orderId;
        }

        //        private String pay_info;
        private String mhdTradeNo;
        private String payInfo;
        private Integer orderId;
    }
}
