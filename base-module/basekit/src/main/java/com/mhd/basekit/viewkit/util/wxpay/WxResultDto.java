package com.mhd.basekit.viewkit.util.wxpay;

import com.example.muheda.functionkit.netkit.model.ModelDto;
import com.google.gson.annotations.SerializedName;

public class WxResultDto extends ModelDto {
    /**
     * code : 200
     * success : true
     * data : {"orderId":615,"mhdTradeNo":"WP2020033136940055750159175","payInfo":{"package":"Sign=WXPay","appid":"wx2ab3dd818ba1061e","sign":"790DD7FE23FF4567350776B32D004E7E","partnerid":"1580994891","prepayid":"wx31144220253053badd79802d1783529500","noncestr":"NS2020033136940310706263983","timestamp":"1585636940"}}
     */
    private DataBean data;

    @Override
    public ModelDto toJsonDto(String jsonString) {
        return null;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * orderId : 615
         * mhdTradeNo : WP2020033136940055750159175
         * payInfo : {"package":"Sign=WXPay","appid":"wx2ab3dd818ba1061e","sign":"790DD7FE23FF4567350776B32D004E7E","partnerid":"1580994891","prepayid":"wx31144220253053badd79802d1783529500","noncestr":"NS2020033136940310706263983","timestamp":"1585636940"}
         */

        private int orderId;
        private String mhdTradeNo;
        private PayInfoBean payInfo;

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getMhdTradeNo() {
            return mhdTradeNo;
        }

        public void setMhdTradeNo(String mhdTradeNo) {
            this.mhdTradeNo = mhdTradeNo;
        }

        public PayInfoBean getPayInfo() {
            return payInfo;
        }

        public void setPayInfo(PayInfoBean payInfo) {
            this.payInfo = payInfo;
        }

        public static class PayInfoBean {
            /**
             * package : Sign=WXPay
             * appid : wx2ab3dd818ba1061e
             * sign : 790DD7FE23FF4567350776B32D004E7E
             * partnerid : 1580994891
             * prepayid : wx31144220253053badd79802d1783529500
             * noncestr : NS2020033136940310706263983
             * timestamp : 1585636940
             */

            @SerializedName("package")
            private String packageX;
            private String appid;
            private String sign;
            private String partnerid;
            private String prepayid;
            private String noncestr;
            private String timestamp;

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }
        }
    }
}
