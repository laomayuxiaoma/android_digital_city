package com.mhd.basekit.viewkit.util;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mhd.basekit.R;
import com.muheda.imageloader.ImageLoader;


/**
 * Created by 13660 on 2018/5/15.
 * 错误页封装类
 */

public class LoadPageShow {


    public static void show(Context context, LinearLayout linearLayout){
        if (linearLayout != null) {
            linearLayout.setVisibility(View.VISIBLE);
            //暂时关闭 不同版本的方法有所改变
           // Glide.with(context).load(R.drawable.loading).into((ImageView) linearLayout.getChildAt(0));
            ImageLoader.loadImageViewDynamicGif(context, R.drawable.loading,(ImageView) linearLayout.getChildAt(0));
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    public static void replaceShow(LinearLayout linearLayout){
        if (linearLayout != null) {
            linearLayout.getChildAt(0).setVisibility(View.VISIBLE);
            linearLayout.getChildAt(1).setVisibility(View.GONE);
            linearLayout.getChildAt(2).setVisibility(View.GONE);
            linearLayout.getChildAt(3).setVisibility(View.GONE);

        }
    }

    public static void diss(LinearLayout linearLayout, int type, final ReplaceInterface replaceInterface){
        if (linearLayout != null) {
            switch (type) {
                case 1:
                    linearLayout.setVisibility(View.GONE);
                    break;
                case 2:
                    linearLayout.getChildAt(0).setVisibility(View.GONE);
                    linearLayout.getChildAt(1).setVisibility(View.VISIBLE);
                    linearLayout.getChildAt(2).setVisibility(View.VISIBLE);
                    linearLayout.getChildAt(3).setVisibility(View.GONE);
                    ((TextView) linearLayout.getChildAt(2)).setText("暂无数据");
                    break;
                case 3:
                    //弃用
                    /*linearLayout.getChildAt(0).setVisibility(View.GONE);
                    linearLayout.getChildAt(1).setVisibility(View.VISIBLE);
                    linearLayout.getChildAt(2).setVisibility(View.GONE);
                    ((TextView) linearLayout.getChildAt(1)).setText("请求失败");*/
                    break;
                case 4:
                    linearLayout.getChildAt(0).setVisibility(View.GONE);
                    linearLayout.getChildAt(1).setVisibility(View.VISIBLE);
                    linearLayout.getChildAt(2).setVisibility(View.VISIBLE);
                    linearLayout.getChildAt(3).setVisibility(View.VISIBLE);
                    ((TextView) linearLayout.getChildAt(2)).setText("请求失败 稍后重试");
                    linearLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            replaceInterface.replace();
                        }
                    });
                    break;
            }
        }
    }

    public interface ReplaceInterface{
        void replace();
    }
}
