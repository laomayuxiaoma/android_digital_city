package com.mhd.basekit.viewkit.view.dialog;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;

import com.example.muheda.loadandshare.ShareApi;
import com.example.muheda.loadandshare.model.ShareContent;
import com.example.muheda.loadandshare.model.ShareType;
import com.mhd.basekit.R;
import com.mhd.basekit.databinding.DialogShareBinding;
import com.mhd.basekit.viewkit.util.wholeCommon.UrlConstant;
import com.muheda.mhdsystemkit.systemUI.dialog.BaseDialogFragment;
import com.muheda.mhdsystemkit.sytemUtil.uiutil.ToastUtils;

/**
 * 创建日期：2019/11/14 on 16:03
 * 描述: 分享弹框
 * 作者: zhangming
 */
public class ShareDialog extends BaseDialogFragment<DialogShareBinding> implements View.OnClickListener {

    private String description;
    private Integer id;

    public ShareDialog() {
        settWindowAnimations(R.style.AnimDownInDownOutOverShoot);
        setGravity(Gravity.BOTTOM);
        setDialogSizeRatio(MATCH_PARENT, WRAP_CONTENT);
        setCancelable(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_share;
    }

    @Override
    protected void initView() {
        mBinding.imgCircleOfFriends.setOnClickListener(this);
        mBinding.imgWechat.setOnClickListener(this);
        mBinding.tvCanel.setOnClickListener(this);
    }

    @Override
    protected void init() {

    }

    public ShareDialog setDescription(String description) {
        this.description = description;
        return this;
    }

    public ShareDialog setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public void onClick(View v) {
//        String url=;
//        String urlEncodee= URLEncoder.encode(url,"utf-8");
        ShareContent shareContent = new ShareContent()
                .setTitle("我从沈阳易充电App给您分享了一个充电站")
                .setDescription(description)
                .setUrl(UrlConstant.HTTP_WEB_URL + "/#/ChargingApp/site_details?title=" + description + "&id=" + id)
                .setDrawId(R.mipmap.share_logo);
        if (v.getId() == R.id.img_circleOfFriends) {
            ShareApi.doShareVeify((Activity) getContext(), ShareType.WXFRIENDSHARE, shareContent, new ShareApi.OnShareListener() {
                @Override
                public void onShareOk() {
                    ToastUtils.showShort("分享成功");
                }

                @Override
                public void onShareFail(String errString) {
                    ToastUtils.showShort("分享失败");
                }
            });
        } else if (v.getId() == R.id.img_wechat) {
            ShareApi.doShareVeify((Activity) getContext(), ShareType.WXSHARE, shareContent, new ShareApi.OnShareListener() {
                @Override
                public void onShareOk() {
                    ToastUtils.showShort("分享成功");
                }

                @Override
                public void onShareFail(String errString) {
                    ToastUtils.showShort("分享失败");
                }
            });
        }
        dismiss();
    }
}
