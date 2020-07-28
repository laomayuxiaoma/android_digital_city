package com.mhd.basekit.viewkit.view.dialog;

import java.lang.reflect.Constructor;
import java.util.HashMap;

/**
 * 创建日期：2019/10/10 on 13:54
 * 描述:
 * 作者: zhangming
 */
public class DialogShowUtil {

    private HashMap<Class, BaseDialogShow> dialogShows = new HashMap<>();

    public BaseDialogShow getDialogWithClass(Class aClass) {
        BaseDialogShow dialog = dialogShows.get(aClass);
        if (dialog != null) {
            return dialog;
        }
        //若不存在则通过反射获取实例
        try {
            //反射需要的BaseView子类，并初始化
            Constructor constructor = aClass.getConstructor();
            BaseDialogShow dialogShow = (BaseDialogShow) constructor.newInstance();
            dialogShows.put(aClass, dialogShow);
            return dialogShow;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
