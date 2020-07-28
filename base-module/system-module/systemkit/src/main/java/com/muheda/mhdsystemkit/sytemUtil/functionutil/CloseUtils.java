package com.muheda.mhdsystemkit.sytemUtil.functionutil;

import java.io.Closeable;

public final class CloseUtils {

    private  CloseUtils(){
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 关闭Closeable对象
     * @param closeables
     */
    public static void closeQuietly(Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
