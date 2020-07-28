package com.muheda.mhdsystemkit.systemUI.dialog;

import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.example.muheda.mhdsystemkit.R;
import com.example.muheda.mhdsystemkit.databinding.GeneralDlgBaseBinding;
import com.muheda.mhdsystemkit.sytemUtil.uiutil.ViewStateUtil;


/**
 * Created by zhangming on 2018/6/11.
 */

public class GeneralDlg extends BaseDialogFragment<GeneralDlgBaseBinding> implements View.OnClickListener {

    private Builder builder;

    private GeneralDlg(Builder builder) {
        super();
        setDialogSizeRatio(0.7, 0);
        this.builder = builder;
        setCancelable(builder.cancelable);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.general_dlg_base;
    }

    @Override
    protected void initView() {
        initTitle();
        initMessage();
        initBtn(mBinding.dialogButtonOk, builder.isHidePositiveButton, builder.btnOkStr, builder.btnOkColor);
        initBtn(mBinding.dialogButtonCancel, builder.isHideNegativeButton, builder.btnNegativeStr, builder.btnNegativeColor);
        initlistener();
    }

    //获取自己想修改的View(为了便于修改，切记此方法要配合UpdateUIListener监听使用
    // 在设置UpdateUIListener监听后即可调用)
    public <T extends View> T findViewById(int id) {
        return mBinding.decor.findViewById(id);
    }

    public void setUpdateUIListener(UpdateUIListener updateUIListener) {
        builder.updateUIListener = updateUIListener;
    }

    private void initBtn(Button btn, boolean isHide, String str, int color) {
        if (isHide) {
            ViewStateUtil.viewGone(btn);
            ViewStateUtil.viewGone(mBinding.lineVertical);
            return;
        }
        if (!TextUtils.isEmpty(str))
            btn.setText(str);
        if (color != 0)
            btn.setTextColor(color);
    }

    private void initMessage() {
        if (builder.mContentView != null) {
            ViewStateUtil.viewGone(mBinding.message);
            mBinding.contentView.addView(builder.mContentView);
            return;
        }
        int gravity = Gravity.LEFT;
        switch (builder.messageLoaction) {
            case CENTER:
                gravity = Gravity.CENTER;
                break;
            case RIGHT:
                gravity = Gravity.RIGHT;
                break;
        }
        mBinding.message.setGravity(gravity);
        mBinding.message.setText(builder.message);
    }

    private void initTitle() {
        if (builder.isHideTitle) {
            ViewStateUtil.viewGone(mBinding.title);
            return;
        }
        int gravity = Gravity.LEFT;
        switch (builder.titleLocation) {
            case CENTER:
                gravity = Gravity.CENTER;
                break;
            case RIGHT:
                gravity = Gravity.RIGHT;
                break;
        }
        mBinding.title.setGravity(gravity);
        mBinding.title.setText(builder.title);
    }

    private void initlistener() {
        mBinding.dialogButtonCancel.setOnClickListener(this);
        mBinding.dialogButtonOk.setOnClickListener(this);
        setOnDismissListener(builder.onDismissListener);
        if (builder.updateUIListener != null)
            builder.updateUIListener.onUpdateUIListener(this);
    }

    @Override
    protected void init() {

    }

    @Override
    public void onClick(View v) {
        dismiss();
        int id = v.getId();
        if (id == R.id.dialog_button_cancel){
            if (builder.btnNegativeListener == null) {
                return;
            }
            builder.btnNegativeListener.onClick(v);
        }else if (id == R.id.dialog_button_ok){
            if (builder.btnOkListener == null) {
                return;
            }
            builder.btnOkListener.onClick(v);
        }
    }

    public interface UpdateUIListener {
        //
        void onUpdateUIListener(GeneralDlg mGeneralDlg);
    }

    public static class Builder {
        private String title;
        private String message;
        private String btnOkStr;
        private String btnNegativeStr;
        private int btnOkColor;
        private int btnNegativeColor;
        private int bgColor;
        private View.OnClickListener btnOkListener;
        private View.OnClickListener btnNegativeListener;
        private boolean cancelable = true;
        private DialogInterface.OnDismissListener onDismissListener;
        private View mContentView;
        private Location titleLocation = Location.LEFT;//位置
        private Location messageLoaction = Location.CENTER;//位置
        private boolean isHidePositiveButton;
        private boolean isHideNegativeButton;
        private boolean isHideTitle;
        private UpdateUIListener updateUIListener;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setTitleLeft() {
            this.titleLocation = Location.LEFT;
            return this;
        }

        public Builder setTitleCenter() {
            this.titleLocation = Location.CENTER;
            return this;
        }

        public Builder setTitleRight() {
            this.titleLocation = Location.RIGHT;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessageLeft() {
            this.messageLoaction = Location.LEFT;
            return this;
        }

        public Builder setMessageCenter() {
            this.messageLoaction = Location.CENTER;
            return this;
        }

        public Builder setMessageRight() {
            this.messageLoaction = Location.RIGHT;
            return this;
        }

        //设置按钮文字
        public Builder setPositiveButton(String btnStr) {
            this.btnOkStr = btnStr;
            return this;
        }

        //设置按钮文字
        public Builder setPositiveButton(View.OnClickListener listener) {
            this.btnOkListener = listener;
            return this;
        }

        public Builder setPositiveButton(String btnStr, View.OnClickListener listener) {
            this.btnOkStr = btnStr;
            this.btnOkListener = listener;
            return this;
        }

        public Builder setNegativeButton(String btnStr) {
            this.btnNegativeStr = btnStr;
            return this;
        }

        public Builder setNegativeButton(View.OnClickListener listener) {
            this.btnNegativeListener = listener;
            return this;
        }

        //设置监听
        public Builder setNegativeButton(String btnStr, View.OnClickListener listener) {
            this.btnNegativeStr = btnStr;
            this.btnNegativeListener = listener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        //设置监听
        public Builder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            this.onDismissListener = onDismissListener;
            return this;
        }

        //设置自定义view
        public Builder setContentView(View contentView) {
            this.mContentView = contentView;
            return this;
        }

        //隐藏按钮
        public Builder hidePositiveButton() {
            this.isHidePositiveButton = true;
            return this;
        }

        //隐藏按钮
        public Builder hideNegativeButton() {
            this.isHideNegativeButton = true;
            return this;
        }

        //设置字体颜色
        public Builder setPositiveButtonTextColor(int color) {
            this.btnOkColor = color;
            return this;
        }

        //设置字体颜色
        public Builder setNegativeButtonTextColor(int color) {
            this.btnNegativeColor = color;
            return this;
        }

        public Builder hideTitle() {
            this.isHideTitle = true;
            return this;
        }

        public Builder setUpdateUIListener(UpdateUIListener updateUIListener) {
            this.updateUIListener = updateUIListener;
            return this;
        }

        public GeneralDlg create() {
            return new GeneralDlg(this);
        }

        public enum Location {
            LEFT, CENTER, RIGHT
        }
    }
}
