package com.example.amapservice;

import java.io.Serializable;

/**
 * 创建日期：2019/11/19 on 18:10
 * 描述:
 * 作者: zhangming
 */
public class ListenerConfig implements Serializable {

    public long interval;
    public int type;
    public OnLocationListener onLocationListener;

}
