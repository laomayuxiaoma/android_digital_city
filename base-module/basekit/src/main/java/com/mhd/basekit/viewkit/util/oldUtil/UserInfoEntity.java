package com.mhd.basekit.viewkit.util.oldUtil;



/**
 * 用户信息
 *
 * @author Administrator
 */
public class UserInfoEntity {

    private String id;
    private String uuid;
    private String userName;
    private String password;
    private String salt;
    private String nickName;
    private String userPic;
    private String mobile;
    private String email;
    private String createTime;
    private String registOrigin;
    private String status;
    private String trueName;
    private String sex;
    private String birthday;
    private String address;
    private String idCard;
    private String idcardFront;
    private String idcardBack;
    private String postCode;
    private String fax;
    private String enableScore;
    private String userType;
    private String failAudtDesc;
    private String isAgreePrivacyProtocol;



    // 冻结积分
    private String freezeSum;

    private int isGroupManager;
    private String groupURL;
    private String groupName;

    public String getIsAgreePrivacyProtocol() {
        return isAgreePrivacyProtocol;
    }

    public void setIsAgreePrivacyProtocol(String isAgreePrivacyProtocol) {
        this.isAgreePrivacyProtocol = isAgreePrivacyProtocol;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getIsGroupManager() {
        return isGroupManager;
    }

    public void setIsGroupManager(int isGroupManager) {
        this.isGroupManager = isGroupManager;
    }

    public String getGroupURL() {
        return groupURL;
    }

    public void setGroupURL(String groupURL) {
        this.groupURL = groupURL;
    }

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
        SPUtil.setString("mUUid", uuid);
        this.uuid = uuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFailAudtDesc() {
        return failAudtDesc;
    }

    public void setFailAudtDesc(String failAudtDesc) {
        this.failAudtDesc = failAudtDesc;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRegistOrigin() {
        return registOrigin;
    }

    public void setRegistOrigin(String registOrigin) {
        this.registOrigin = registOrigin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdcardFront() {
        return idcardFront;
    }

    public void setIdcardFront(String idcardFront) {
        this.idcardFront = idcardFront;
    }

    public String getIdcardBack() {
        return idcardBack;
    }

    public void setIdcardBack(String idcardBack) {
        this.idcardBack = idcardBack;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEnableScore() {
        return enableScore;
    }

    public void setEnableScore(String enableScore) {
        this.enableScore = enableScore;
    }

    public String getFreezeSum() {
        return freezeSum;
    }

    public void setFreezeSum(String freezeSum) {
        this.freezeSum = freezeSum;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


}
