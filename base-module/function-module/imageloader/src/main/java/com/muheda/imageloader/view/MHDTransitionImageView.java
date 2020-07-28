package com.muheda.imageloader.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;


/**
 * @author zhangming
 * @Date 2019/2/19 13:56
 * @Description: 用于自定义过渡动画(配合imageloader使用)
 */
public class MHDTransitionImageView extends ImageView implements Runnable, ValueAnimator.AnimatorUpdateListener {

    private Drawable mShadeDrawable;
    private ValueAnimator mValueAnimator;
    private int mShadeWidth = 0;
    private int mShadeHeight = 0;
    private int mShowY;
    private float mShadeScale;
    private long mDuration = 200;

    private Matrix matrix = new Matrix();
    private int mShadeTranslateX;
    private int mShadeTranslateY;

    public MHDTransitionImageView(Context context) {
        this(context, null);
    }

    public MHDTransitionImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MHDTransitionImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MHDTransitionImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        if (getDrawable() == null) {
            return; // couldn't resolve the URI
        }

        if (getDrawable().getIntrinsicWidth() == 0 || getDrawable().getIntrinsicHeight() == 0) {
            return;     // nothing to draw (empty bounds)
        }
        if (mShadeDrawable == null) {
            mShadeDrawable = drawable;
            mShadeWidth = getDrawable().getIntrinsicWidth();
            mShadeHeight = getDrawable().getIntrinsicHeight();
            matrix = getImageMatrix();
            initAnimator();
        }
    }

    private void initAnimator() {
        this.post(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mShadeDrawable != null && getDrawable() != null &&
                mShadeWidth != getDrawable().getIntrinsicWidth() && mShadeHeight != getDrawable().getIntrinsicHeight()) {
            canvas.save();
            canvas.clipRect(0, mShowY, getMeasuredWidth(), getMeasuredHeight());
            matrix.setScale(getShadeScale(), getShadeScale());
            matrix.postTranslate(mShadeTranslateX, mShadeTranslateY);
            canvas.setMatrix(matrix);
            mShadeDrawable.draw(canvas);
            canvas.restore();
            canvas.save();
            canvas.clipRect(0, 0, getMeasuredWidth(), mShowY);
        }

        super.onDraw(canvas);
    }

    private float getShadeScale() {
        if (mShadeScale != 0) {
            return mShadeScale;
        }
        if (mShadeDrawable == null || mShadeDrawable.getIntrinsicWidth() == 0 ||
                mShadeDrawable.getIntrinsicHeight() == 0 || getMeasuredHeight() == 0 || getMeasuredWidth() == 0) {
            return 1.0f;
        }
        mShadeScale = (float) (getMeasuredWidth()) / mShadeDrawable.getIntrinsicWidth();
        if (mShadeScale < getMeasuredHeight() / (float) mShadeDrawable.getIntrinsicHeight()) {
            mShadeScale =  getMeasuredHeight() /(float)mShadeDrawable.getIntrinsicHeight();
            mShadeTranslateX = -(int) ((mShadeDrawable.getIntrinsicWidth() * mShadeScale - getMeasuredWidth()) / 2);
        } else {
            mShadeTranslateY = -(int) ((mShadeDrawable.getIntrinsicHeight() * mShadeScale - getMeasuredHeight()) / 2);
        }
        return mShadeScale;
    }

    @Override
    public void run() {
        mValueAnimator = ValueAnimator.ofInt(0, getMeasuredHeight());
        mValueAnimator.setDuration(mDuration);
        mValueAnimator.addUpdateListener(this);
        mValueAnimator.start();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        mShowY = (int) animation.getAnimatedValue();
        MHDTransitionImageView.this.postInvalidate();
        if (animation.getAnimatedFraction() == 100) {
            mShadeDrawable = null;
        }
    }
}