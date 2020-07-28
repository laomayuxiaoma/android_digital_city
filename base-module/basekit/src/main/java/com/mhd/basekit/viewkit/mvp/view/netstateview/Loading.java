package com.mhd.basekit.viewkit.mvp.view.netstateview;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mhd.basekit.R;
import com.mhd.basekit.databinding.NetLoadingBinding;
import com.muheda.imageloader.ImageLoader;
import com.muheda.mhdsystemkit.systemUI.stateView.BaseView;

import java.util.HashMap;

/**
 * 加载中
 */
public class Loading extends BaseView<Object, NetLoadingBinding> {


    public Loading(Context context, Object data, ViewGroup parent) {
        super(context, data, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.net_loading;
    }

    @Override
    protected void initView(View view, boolean isUpdate) {
        if (!isUpdate) {
            ImageLoader.loadImageViewDynamicGif(view.getContext(), R.mipmap.data_loading, (ImageView) view.findViewById(R.id.iv_load));
            Context context = view.getContext();
            if (context instanceof Activity) {
                View decorView = ((Activity) context).getWindow().getDecorView();
                View root = decorView.findViewById(R.id.layout_root);
                View netView = decorView.findViewById(R.id.ll_load_page);
                if (root != null && netView != null && (root.getBackground() instanceof ColorDrawable) && ((ColorDrawable) root.getBackground()).getColor() == context.getResources().getColor(R.color.color_f7f7f7)) {
                    ImageLoader.loadImageViewDynamicGif(view.getContext(), R.mipmap.data_loading_grey, (ImageView) view.findViewById(R.id.iv_load));
                    netView.setBackground(root.getBackground());
                }
            }
        }
    }

    @Override
    protected void initListener(View view, boolean isUpdate) {

    }

    @Override
    protected void initViewConfigure(HashMap<String, Class> viewConfigure) {

    }

    @Override
    protected ConbinationBuilder combinationViewBuilder() {
        return null;
    }
}
