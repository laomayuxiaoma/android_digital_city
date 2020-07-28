package com.muheda.idas;

/**
 * @author zhangming
 * @Date 2019/7/3 10:45
 * @Description: 三急算法
 */
public class Idas {

    static {
        System.loadLibrary("carmodel");
    }

    public native int initModelData(double acc[], double slow[], double turn[], int nMax);

    public native int[] carmodel(double x, double y, long t);

    public native int carmodel2(double x, double y, long t);

    public native int clearCache();

}
