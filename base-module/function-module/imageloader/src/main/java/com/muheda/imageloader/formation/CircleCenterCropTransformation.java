package com.muheda.imageloader.formation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.security.MessageDigest;

/**
 * 圆形图片(centerCrop)
 * Created by zhangming on 2018/6/7.
 */

public class CircleCenterCropTransformation extends CustomCenterCropTransformation {

    private static final String  ID = "com.muheda.imageloader.formation.CircleCenterCropTransformation";
    private static final byte[]  ID_BYTES = ID.getBytes(CHARSET);

    public CircleCenterCropTransformation(Context context) {
        super(context);
    }

    @Override
    protected void drawMaskLayer(Canvas canvas, int width, int height, Paint paint) {
        canvas.drawCircle(width / 2, height / 2, Math.min(width / 2, height / 2), paint);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof CircleCenterCropTransformation;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}
