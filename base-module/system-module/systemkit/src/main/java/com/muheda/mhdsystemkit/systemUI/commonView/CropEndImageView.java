package com.muheda.mhdsystemkit.systemUI.commonView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * 图片显示控件，宽高自适应，外部调用图片模式统一为fitXY
 * 宽度为精准模式时以宽度为准（当宽高都为精准模式时以宽度为准），
 * 当宽度不为精准模式而高度为精准模式时才以高度为准
 * Created by zhangming on 2018/05/30.
 */
public class CropEndImageView extends ImageView {

    public CropEndImageView(Context context) {
        super(context);
    }

    public CropEndImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CropEndImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        final Drawable d = this.getDrawable();
        if (d != null) {
            // ceil not round - avoid thin vertical gaps along the left/right edges

            int widthMode = getLayoutParams().width;
            int heightMode = getLayoutParams().height;
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = (int) Math.ceil(width * (float) d.getIntrinsicHeight() / d.getIntrinsicWidth());
            if (widthMode != LinearLayout.LayoutParams.WRAP_CONTENT ||
                    (widthMode != LinearLayout.LayoutParams.WRAP_CONTENT &&
                            heightMode != LinearLayout.LayoutParams.WRAP_CONTENT)){
                width = MeasureSpec.getSize(widthMeasureSpec);
                height = (int) Math.ceil(width * (float) d.getIntrinsicHeight() / d.getIntrinsicWidth());
            }else if (heightMode != LinearLayout.LayoutParams.WRAP_CONTENT){
                height = MeasureSpec.getSize(heightMeasureSpec);
                width = (int) Math.ceil(height * (float) d.getIntrinsicWidth() / d.getIntrinsicHeight());
            }
            this.setMeasuredDimension(MeasureSpec.makeMeasureSpec(width,MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY));
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
