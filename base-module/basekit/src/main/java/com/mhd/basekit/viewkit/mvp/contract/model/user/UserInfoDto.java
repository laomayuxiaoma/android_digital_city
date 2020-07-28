package com.mhd.basekit.viewkit.mvp.contract.model.user;

import com.example.muheda.functionkit.netkit.model.ModelDto;

/**
 * @author zhangming
 * @Date 2019/6/17 17:52
 * @Description: 用户数据
 */
public class UserInfoDto extends ModelDto {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {

        private String id;
        private String uuid;
        private String password;
        private String mobile;
        private String createTime;
        private String contactName;
        private String contactMobile;
        private String nickName;
        private String userPic = "";

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getContactMobile() {
            return contactMobile;
        }

        public void setContactMobile(String contactMobile) {
            this.contactMobile = contactMobile;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getUserPic() {
            return userPic;
        }

        public void setUserPic(String userPic) {
            this.userPic = userPic;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "id='" + id + '\'' +
                    ", uuid='" + uuid + '\'' +
                    ", password='" + password + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", contactName='" + contactName + '\'' +
                    ", contactMobile='" + contactMobile + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", userPic='" + userPic + '\'' +
                    '}';
        }
    }

    @Override
    public ModelDto toJsonDto(String s) {
        return null;
    }

    @Override
    public String toString() {
        return "UserInfoDto{" +
                "data=" + data +
                '}';
    }
}
