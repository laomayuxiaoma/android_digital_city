package com.muheda.communicationinterface.util;

import com.muheda.communicationinterface.model.BaseCommunicationModel;

import java.lang.reflect.Method;

/**
 * @author zhangming
 * @Date 2019/6/13 11:07
 * @Description: 通信帮助类（用于一个被调用类有多个方法可能被调用时）
 */
public class ConmunicationHelper {

    /**
     * @param context  要反射的对象实例
     * @param params 通信参数
     * @param <T>
     */
    public static <T extends BaseCommunicationModel> Object invokeMethod(Object context, T params) {
        try {
            Class cla = context.getClass();
            Method declaredMethod = cla.getDeclaredMethod(params.getMethodName(), params.getClass());
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(context, params);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
