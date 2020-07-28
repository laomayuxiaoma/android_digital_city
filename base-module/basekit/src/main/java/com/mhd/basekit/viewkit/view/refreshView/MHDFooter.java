package com.mhd.basekit.viewkit.view.refreshView;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mhd.basekit.R;
import com.muheda.customrefreshlayout.ICustomFooter;
import com.muheda.imageloader.ImageLoader;
import com.muheda.mhdsystemkit.sytemUtil.uiutil.SizeUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;

/**
 * @author zhangming
 * @Date 2019/1/11 17:42
 * @Description: 底部加载更多
 */
public class MHDFooter extends ICustomFooter {

    private ImageView mFooterImageView;
    private TextView mFooterTextView;

    public MHDFooter(Context context) {
        super(context);
    }

    public MHDFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MHDFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initView(View view) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.height = SizeUtils.dp2px(60);
        this.setLayoutParams(layoutParams);
        mFooterImageView = (ImageView) view
                .findViewById(R.id.pull_to_load_image);

        ImageLoader.loadImageViewDynamicGif(getContext(), R.mipmap.refresh, mFooterImageView);
        Context context = view.getContext();
        if (context instanceof Activity) {
            View decorView = ((Activity) context).getWindow().getDecorView();
            View root = decorView.findViewById(R.id.layout_root);
            if (root != null && (root.getBackground() instanceof ColorDrawable) && ((ColorDrawable) root.getBackground()).getColor() == getResources().getColor(R.color.color_f7f7f7)) {
                ImageLoader.loadImageViewDynamicGif(getContext(), R.mipmap.refresh_grey, mFooterImageView);
            }
        }
        mFooterTextView = (TextView) view
                .findViewById(R.id.pull_to_load_text);
    }

    @Override
    public int getLayoutId() {
        return R.layout.refresh_footer;
    }

    @Override
    protected void onCustomFinish(RefreshLayout refreshLayout, boolean success) {

    }

    @Override
    protected void onCustomStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {

    }

    @Override
    protected void onCustomMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public boolean setNoMoreData(boolean noMoreData) {
        return false;
    }
}
