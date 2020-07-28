package com.mhd.basekit.viewkit.view.webview.util;

/**
 * @author zhangming
 * @Date 2019/5/7 11:41
 * @Description: js交互时的格式
 * js与Android互调格式
 * {
 *   method: //方法名
 *   data: {
 * 	list: []
 *            }
 * }
 *
 * method：互调时规定的要调用的方法名
 * data中存放需要传递的参数
 * 当data中的下一级是一个list列表时，data的下一级写list:[]
 *
 * 命名规则：
 * 调用app的方法以app开头：app+功能名称
 * 调用h5的方法以js开头：js+功能名称
 *
 * 部分方法比如：
 * app调用h5：
 *
 * jsCancel   ：取消操作
 *
 * h5调用app方法：
 *
 * appActivityFinish  ： 关闭activity
 *
 *
 * 回传时格式confirm为false时不需要传递data内容，当为true时且需要回传信息时，信息写在data中。
 * {
 *   confirm: ture || false,
 *   data: {
 *
 *   }
 * }
 */
public class JsDto {

    protected String method;

    public JsDto() {
        initMethod();
    }

    protected void initMethod() {

    }

    public JsDto(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
