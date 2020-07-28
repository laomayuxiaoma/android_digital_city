package com.muheda.mdsearchview.model;

import android.view.View;

import com.muheda.mdsearchview.icallback.ICallBack;

import java.util.List;

/**
 * @author wangfei
 * @date 2019/7/24.
 */
public class SearchDataDto extends SearchModelDto {

    private String title;
    private List<MySearchDto.SearchDto.DataBean> data;

    private View.OnClickListener onClickListener = null;
    private ICallBack onCallBackListener = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MySearchDto.SearchDto.DataBean> getData() {
        return data;
    }

    public void setData(List<MySearchDto.SearchDto.DataBean> data) {
        this.data = data;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public ICallBack getOnCallBackListener() {
        return onCallBackListener;
    }

    public void setOnCallBackListener(ICallBack onCallBackListener) {
        this.onCallBackListener = onCallBackListener;
    }

}
