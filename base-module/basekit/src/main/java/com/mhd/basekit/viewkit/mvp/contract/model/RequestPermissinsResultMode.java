package com.mhd.basekit.viewkit.mvp.contract.model;

/**
 * @author zhangming
 * @Date 2019/5/16 15:15
 * @Description: 申请权限结果回调时对外抛出的对象
 */
public class RequestPermissinsResultMode {

    private int requestCode;
    private String permissions[];
    private int[] grantResults;

    public RequestPermissinsResultMode(int requestCode, String[] permissions, int[] grantResults) {
        this.requestCode = requestCode;
        this.permissions = permissions;
        this.grantResults = grantResults;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }

    public int[] getGrantResults() {
        return grantResults;
    }

    public void setGrantResults(int[] grantResults) {
        this.grantResults = grantResults;
    }
}
