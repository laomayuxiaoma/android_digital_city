package com.muheda.imageloader.formation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.NonNull;

import java.security.MessageDigest;

/**
 * 圆形图片(fitCenter)
 * Created by zhangming on 2018/6/7.
 */

public class CircleFitCenterTransformation extends CustomFitCenterTransformation {

    private static final String  ID = "com.muheda.imageloader.formation.CircleFitCenterTransformation";
    private static final byte[]  ID_BYTES = ID.getBytes(CHARSET);

    public CircleFitCenterTransformation(Context context) {
        super();
    }

    @Override
    protected void drawMaskLayer(Canvas canvas, int width, int height, Paint paint) {
        canvas.drawCircle(width / 2, height / 2, Math.min(width / 2, height / 2), paint);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof CircleFitCenterTransformation;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}
