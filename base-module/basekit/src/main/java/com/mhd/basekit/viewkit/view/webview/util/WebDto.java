package com.mhd.basekit.viewkit.view.webview.util;

/**
 * @author zhangming
 * @Date 2019/5/7 11:18
 * @Description: eventBus跳转数据实体类
 */
public class WebDto {
    private String method;
    private Object data = new Object();

    public WebDto(String method) {
        this.method = method;
    }

    public WebDto(String method, Object data) {
        this.method = method;
        this.data = data;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
