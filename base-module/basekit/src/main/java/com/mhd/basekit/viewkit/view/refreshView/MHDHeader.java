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
import com.muheda.customrefreshlayout.ICustomHeader;
import com.muheda.imageloader.ImageLoader;
import com.muheda.mhdsystemkit.sytemUtil.uiutil.SizeUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;


/**
 * @author zhangming
 * @Date 2019/1/11 16:32
 * @Description: 刷新头部
 */
public class MHDHeader extends ICustomHeader {

    public static String[] note_refreshing = {"看见红灯别瞎跑，胡闹", "定期保养很重要", "看到交警问声好", "别看我车小，车位很好找", "滴滴滴，啪啪啪",
            "不要靠的那么近，会吻上的", "犯困别开车，开车别犯困", "注意前方，别看美女", "你觉得你很猛，卡车比你还要猛", "注意，你开的不是碰碰车",
            "上车系好安全带", "新手上路莫慌张", "下车需要前后看", "雪天容易打滑请握紧方向盘", "居民区行驶减少鸣笛",
            "学校前方需慢行", "行车不要玩手机", "地球是我的家，绿化靠大家", "带上平安上路，载着幸福回家", "没有红灯的停，就没有绿灯的行",
            "保护水资源，生命真永远", "关心未来资源，减排不是终点", "真的没有了"};
    private int count = 0;

    private ImageView mHeaderImageView;
    private TextView mHeaderTextView;

    public MHDHeader(Context context) {
        super(context);
    }

    public MHDHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MHDHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initView(View view) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.height = SizeUtils.dp2px(70);
        this.setLayoutParams(layoutParams);
        mHeaderImageView = (ImageView) view
                .findViewById(R.id.pull_to_refresh_image);

        Context context = view.getContext();
        ImageLoader.loadImageViewDynamicGif(getContext(), R.mipmap.refresh, mHeaderImageView);
        if (context instanceof Activity){
            View decorView = ((Activity) context).getWindow().getDecorView();
            View root = decorView.findViewById(R.id.layout_root);
            if (root != null && (root.getBackground() instanceof ColorDrawable) && ((ColorDrawable)root.getBackground()).getColor() == getResources().getColor(R.color.color_f7f7f7)){
                ImageLoader.loadImageViewDynamicGif(getContext(), R.mipmap.refresh_grey, mHeaderImageView);
            }
        }

//        mHeaderTextView = (TextView) view
//                .findViewById(R.id.pull_to_refresh_text);
//        mHeaderTextView.setText(note_refreshing[count]);
    }

    @Override
    public int getLayoutId() {
        return R.layout.refresh_header;
    }

    @Override
    protected void onCustomFinish(RefreshLayout refreshLayout, boolean success) {
//        if (count < note_refreshing.length - 2) {
//            count++;
//        } else {
//            count = 0;
//        }
//        mHeaderTextView.setText(note_refreshing[count]);
    }

    @Override
    protected void onCustomStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {

    }

    @Override
    protected void onCustomMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }
}
