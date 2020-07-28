package com.muheda.mhdsystemkit.systemUI.commonView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;


/**
 * @author zhangming
 * @Date 2018/11/29 18:09
 * @Description: 自定义TextView
 */
public class MHDTextView extends TextView {
    public MHDTextView(Context context) {
        super(context);
        init(context);
    }

    public MHDTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MHDTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MHDTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        //去掉周围的边距
        this.setIncludeFontPadding(false);
    }
}
