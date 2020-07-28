package com.muheda.customtitleview;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.muheda.customtitleview.listener.OnNestedScrollChangeListener;
import com.muheda.customtitleview.listener.OnRecycleScrollChangeListenerImpl;
import com.muheda.customtitleview.listener.OnScrollChangeListenerImpl;
import com.muheda.customtitleview.util.DisplayUtil;

public class CustomTitleView extends RelativeLayout {

    private View rootBg;
    private View rootView;

    private ITitleView titleLeft;
    private ITitleView titleCenter;
    private ITitleView titleRight;
    private ITitleView customTitle;
    private ITitleView titleBottom;
    private ImageView bottomLine;

    private OnNestedScrollChangeListener mOnNestedScrollChangeListener;

    private float alph;
    /**
     * title在变化时是否其上的内容也跟着变化
     */
    private boolean isAlphChangeWithTitle = false;
    /**
     * 初始时title是否显示
     */
    private boolean initIsShow = true;
    /**
     * 渐变时的开始距离
     */
    private int startChangeAlphaDisY = 0;

    private int totalDisY = 0;

    private float changeColorDistance = DisplayUtil.dip2px(getContext(), 400);


    public CustomTitleView(Context context) {
        this(context, null);
    }

    public CustomTitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.title_container, this);
        rootBg = findViewById(R.id.bg);
        bottomLine = findViewById(R.id.iv_title_bar);
        if (getContext() instanceof Activity) {
//            String title = ((Activity) getContext()).getTitle().toString();
            setTitle(((Activity) getContext()).getTitle().toString());
        }
        findViewById(R.id.ll_left).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getContext();
                if (context instanceof Activity) {
                    ((Activity) context).finish();
                }
            }
        });
        findViewById(R.id.ll_close).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getContext();
                if (context instanceof Activity) {
                    ((Activity) context).finish();
                }
            }
        });
    }

    /**
     * 自定义左侧布局
     *
     * @param titleView
     */
    public CustomTitleView setTitleLeftLayout(ITitleView titleView) {
        titleLeft = titleView;
        replaceView(R.id.container_left, titleView.getView());
        return this;
    }

    /**
     * 自定义中间布局
     *
     * @param titleView
     */
    public CustomTitleView setTitleCenterLayout(ITitleView titleView) {
        titleCenter = titleView;
        replaceView(R.id.container_center, titleView.getView());
        return this;
    }

    /**
     * 自定义右侧布局
     *
     * @param titleView
     */
    public CustomTitleView setTitleRightLayout(ITitleView titleView) {
        titleRight = titleView;
        replaceView(R.id.container_right, titleView.getView());
        return this;
    }

    /**
     * 自定义底部布局
     *
     * @param titleView
     */
    public CustomTitleView setTitleBottomLayout(ITitleView titleView) {
        titleBottom = titleView;
        replaceView(R.id.container_bottom, titleView.getView());
        return this;
    }

    /**
     * 顶部全自定义布局
     *
     * @param titleView
     */
    public CustomTitleView setCustomTitle(ITitleView titleView) {
        titleLeft = null;
        titleRight = null;
        titleCenter = null;
        customTitle = titleView;
        replaceView(R.id.title_root, titleView.getView());
        return this;
    }

    private void replaceView(int id, View view) {
        ViewGroup viewGroup = findViewById(id);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
    }

    public CustomTitleView enableLeftShow(boolean isShow) {
        if (titleLeft == null) {
            findViewById(R.id.ll_left).setVisibility(isShow ? VISIBLE : GONE);
        }
        return this;
    }

    public CustomTitleView enableCloseShow(boolean isShow) {
        if (titleLeft == null) {
            findViewById(R.id.ll_close).setVisibility(isShow ? VISIBLE : GONE);
        }
        return this;
    }

    public CustomTitleView enableCenterShow(boolean isShow) {
        if (titleCenter == null) {
            findViewById(R.id.container_title).setVisibility(isShow ? VISIBLE : GONE);
        }
        return this;
    }

    public CustomTitleView enableRightShow(boolean isShow) {
        if (titleRight == null) {
            findViewById(R.id.ll_right).setVisibility(isShow ? VISIBLE : GONE);
        }
        return this;
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public CustomTitleView setTitle(String title) {
        if (titleCenter == null) {
            ((TextView) findViewById(R.id.container_title)).setText(title);
        }
        return this;
    }

    /**
     * 设置标题字体大小
     */
    public CustomTitleView setTitleSize(float size) {
        if (titleCenter == null) {
            ((TextView) findViewById(R.id.container_title)).setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        }
        return this;
    }

    /**
     * 设置标题字体颜色
     */
    public CustomTitleView setTitleColor(int color) {
        if (titleCenter == null) {
            ((TextView) findViewById(R.id.container_title)).setTextColor(color);
        }
        return this;
    }

    /**
     * 设置右侧标题
     */
    public CustomTitleView setRightText(String str) {
        if (titleRight == null) {
            ((TextView) findViewById(R.id.title_right)).setText(str);
        }
        return this;
    }

    /**
     * 设置右侧标题字体大小
     */
    public CustomTitleView setRightTextSize(float size) {
        if (titleRight == null) {
            ((TextView) findViewById(R.id.title_right)).setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        }
        return this;
    }

    /**
     * 设置右侧标题字体颜色
     */
    public CustomTitleView setRightTextColor(int color) {
        if (titleRight == null) {
            ((TextView) findViewById(R.id.title_right)).setTextColor(color);
        }
        return this;
    }

    /**
     * 设置右侧监听事件
     */
    public CustomTitleView setRightListener(OnClickListener listener) {
        if (titleRight == null) {
            findViewById(R.id.ll_right).setOnClickListener(listener);
        } else {
            titleRight.getView().setOnClickListener(listener);
        }
        return this;
    }

    /**
     * 设置左侧监听事件
     */
    public CustomTitleView setLeftListener(OnClickListener listener) {
        if (titleLeft == null) {
            findViewById(R.id.ll_left).setOnClickListener(listener);
        } else {
            titleLeft.getView().setOnClickListener(listener);
        }
        return this;
    }

    public CustomTitleView setLeftImage(int resid) {
        if (titleLeft == null) {
            findViewById(R.id.title_img_left).setBackgroundResource(resid);
        }
        return this;
    }

    public CustomTitleView setRootBackgroundResource(int resid) {
        if (rootBg != null) {
            rootBg.setBackgroundResource(resid);
        }
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public CustomTitleView setRootBackground(Drawable background) {
        if (rootBg != null) {
            rootBg.setBackground(background);
        }
        return this;
    }

    public CustomTitleView setRootBackgroundColor(int color) {
        if (rootBg != null) {
            rootBg.setBackgroundColor(color);
        }
        return this;
    }

    public CustomTitleView setRootBgView(View rootBg) {
        this.rootBg = rootBg;
        return this;
    }

    public CustomTitleView setRootView(View rootBg) {
        this.rootView = rootBg;
        return this;
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    public void attachScrollView(NestedScrollView view) {
        attachScrollView(view, null);
    }

    public void attachScrollView(RecyclerView view) {
        attachScrollView(view, null);
    }

    public void attachScrollView(NestedScrollView view, NestedScrollView.OnScrollChangeListener listener) {
        new OnScrollChangeListenerImpl().attachView(view, this, listener);
    }

    public void attachScrollView(RecyclerView view, RecyclerView.OnScrollListener listener) {
        new OnRecycleScrollChangeListenerImpl().attachView(view, this, listener);
    }

    /**
     * 设置标题的透明度
     *
     * @param disY  距离
     * @param isAdd 距离是否相加（）
     * @return
     */
    public CustomTitleView changTitleAlphaWithDis(int disY, boolean isAdd) {
        if (isAdd) {
            totalDisY += disY;
            disY = totalDisY;
        }
        disY -= startChangeAlphaDisY;
        if (disY < 0)
            disY = 0;
        alph = (float) ((changeColorDistance - disY) / changeColorDistance);
        if (alph < 0)
            alph = 0;
        Log.e("TTTTTTTTTTTTT", disY + "          ||      " + alph + "   ||  " + (1 - alph) + "  ||  " + startChangeAlphaDisY);
        setBgAlpha(alph);
        return this;
    }

    public CustomTitleView setBgAlpha(float alpha) {
        if (!initIsShow) {
            rootView.setAlpha(1 - alpha);
        } else if (isAlphChangeWithTitle) {
            rootView.setAlpha(alpha);
        } else if (rootBg != null) {
            rootBg.setAlpha(alpha);
            if (bottomLine.getVisibility() == VISIBLE) {
                bottomLine.setAlpha(alpha);
            }
        }
        return this;
    }

    public CustomTitleView enableBottomLineShow(boolean isShow) {
        if (bottomLine != null) {
            bottomLine.setVisibility(isShow ? VISIBLE : GONE);
        }
        return this;
    }

    public CustomTitleView setBottomLineBackgroundResource(@DrawableRes int resid) {
        if (bottomLine != null) {
            bottomLine.setBackgroundResource(resid);
        }
        return this;
    }

    public CustomTitleView setBottomLineBackgroundColor(@ColorInt int color) {
        if (bottomLine != null) {
            bottomLine.setBackgroundColor(color);
        }
        return this;
    }

    /**
     * 设置一个完整的渐变过程的距离
     *
     * @param changeColorDistance
     */
    public CustomTitleView setChangeColorDistance(int changeColorDistance) {
        this.changeColorDistance = DisplayUtil.dip2px(getContext(), changeColorDistance);
        return this;
    }

    public CustomTitleView setAlphChangeWithTitle(boolean alphChangeWithTitle) {
        isAlphChangeWithTitle = alphChangeWithTitle;
        return this;
    }

    public CustomTitleView setInitIsShow(boolean initIsShow) {
        this.initIsShow = initIsShow;
        initTitileStatue();
        return this;
    }

    private CustomTitleView initTitileStatue() {
        if (!initIsShow) {
            rootView.setAlpha(0);
            bottomLine.setAlpha(0);
        }
        return this;
    }

    public CustomTitleView setStartChangeAlphaDisY(int startChangeAlphaDisY) {
        this.startChangeAlphaDisY = DisplayUtil.dip2px(getContext(), startChangeAlphaDisY);
        return this;
    }

    public ITitleView getTitleLeftLayout() {
        return titleLeft;
    }

    public ITitleView getTitleCenterLayout() {
        return titleCenter;
    }

    public ITitleView getTitleRightLayout() {
        return titleRight;
    }

    public ITitleView getCustomTitleLayout() {
        return customTitle;
    }

    public ITitleView getTitleBottomLayout() {
        return titleBottom;
    }
}
