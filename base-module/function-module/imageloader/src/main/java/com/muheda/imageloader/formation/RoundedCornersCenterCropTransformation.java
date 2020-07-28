package com.muheda.imageloader.formation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import androidx.annotation.NonNull;

import com.muheda.imageloader.util.DisplayUtil;

import java.security.MessageDigest;


/**
 * 圆角图片(centerCrop)
 * Created by zhangming on 2018/6/7.
 */

public class RoundedCornersCenterCropTransformation extends CustomCenterCropTransformation {
    private int mRadius;

    private static final String  ID = "com.muheda.imageloader.formation.RoundedCornersCenterCropTransformation";
    private static final byte[]  ID_BYTES = ID.getBytes(CHARSET);

    public RoundedCornersCenterCropTransformation(Context context, int mRadius) {
        super(context);
        this.mRadius = DisplayUtil.dip2px(context,mRadius);
    }

    @Override
    protected void drawMaskLayer(Canvas canvas, int width, int height, Paint paint) {
        canvas.drawRoundRect(new RectF(0, 0, width, height), mRadius, mRadius, paint);
    }

    @Override
    public boolean equals(Object o) {
//        if (o instanceof RoundedCornersCenterCropTransformation) {
//            RoundedCornersCenterCropTransformation other = (RoundedCornersCenterCropTransformation) o;
//            return mRadius == other.mRadius;
//        }
        return o instanceof RoundedCornersCenterCropTransformation;
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
