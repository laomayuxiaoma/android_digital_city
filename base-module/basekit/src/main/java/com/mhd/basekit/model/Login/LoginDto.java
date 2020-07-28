package com.mhd.basekit.model.Login;

import com.example.muheda.functionkit.netkit.model.ModelDto;

/**
 * 创建日期：2019/11/25 on 15:19
 * 描述:
 * 作者: zhangming
 */
public class LoginDto extends ModelDto {

    /**
     * code : 200
     * success : true
     * data : {"token":"ff10d235791d1399801d5fbf0476c136"}
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
         * token : ff10d235791d1399801d5fbf0476c136
         */

        private String token;
        private String mobile;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
