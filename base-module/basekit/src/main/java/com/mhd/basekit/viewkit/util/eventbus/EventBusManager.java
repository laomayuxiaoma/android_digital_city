package com.mhd.basekit.viewkit.util.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * @author zhangming
 * @Date 2019/6/20 16:01
 * @Description: Eventbus管理类
 */
public class EventBusManager {

    public static void register(Object subscriber) {
        if (!EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().register(subscriber);
        }
    }

    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }


    public static void post(Object event) {
        EventBus.getDefault().post(event);
    }
}
