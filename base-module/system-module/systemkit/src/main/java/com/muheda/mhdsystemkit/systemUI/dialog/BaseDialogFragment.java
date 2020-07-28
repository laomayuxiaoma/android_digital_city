package com.muheda.mhdsystemkit.systemUI.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.muheda.mhdsystemkit.R;

import org.greenrobot.eventbus.EventBus;


/**
 * DialogFragment扩展基类
 */
public abstract class BaseDialogFragment<VB extends ViewDataBinding> extends DialogFragment implements
        DialogInterface.OnDismissListener, DialogInterface.OnCancelListener {

    public static final int MATCH_PARENT = -2;
    public static final int WRAP_CONTENT = 0;

    protected VB mBinding;

    private Dialog dialog;
    private int mWidth;
    private int mHeight;
    private int mGravity;
    public float dimAmount = -1;
    private int mAnimationsResId;
    private boolean mCancelable = true;

    //需使用者传当前activity或fragment
    private Object mActivity;
    //定位view
    protected View baseView;
    private ViewGroup contentParent;
    private int[] offsetPosition = new int[2];
    private int[] offsetChildPosition = new int[2];
    private boolean isFristCreate = true;

    protected View topView;

    private DialogInterface.OnDismissListener onDismissListener;
    private DialogInterface.OnCancelListener onCancelListener;

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void init();

    //不同的动画效果需在子类中重写构造方法
    public BaseDialogFragment() {
        settWindowAnimations(R.style.AnimScaleInScaleOutOverShoot);
        setGravity(Gravity.CENTER);
        setDialogSizeRatio(WRAP_CONTENT, WRAP_CONTENT);
        setCancelable(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), null, false);
        initView();
        init();
        dialog = getDialog();
        dialog.setCanceledOnTouchOutside(mCancelable);
        return mBinding.getRoot();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity(), R.style.Dialog);
        return dialog;
    }

    /**
     * 设置是否可以点击外侧消失
     *
     * @param mCancelable 是否可以点击外侧消失
     */
    public void setCanceledOnTouchOutside(boolean mCancelable) {
        this.mCancelable = mCancelable;
    }

    /**
     * 设置透明度dialog的背景透明度
     *
     * @param dimAmount dialog背景透明度
     */
    public void setDimAmount(float dimAmount) {
        this.dimAmount = dimAmount;
    }

    /**
     * 设置蒙层所需要的必要参数
     *
     * @param mActivity 当前activity或fragment
     * @param baseView  基准view
     */
    public void setmActivityAndView(Object mActivity, View baseView) {
        this.mActivity = mActivity;
        this.baseView = baseView;
    }

    private void handleRelativePosition() {
        if (mActivity == null || baseView == null) {
            return;
        }

        if (mActivity instanceof Activity) {
            contentParent = ((Activity) mActivity).getWindow().getDecorView().findViewById(android.R.id.content);
        } else if (mActivity instanceof FragmentActivity) {
            contentParent = ((FragmentActivity) mActivity).getWindow().getDecorView().findViewById(android.R.id.content);
        } else if (mActivity instanceof Fragment) {
            contentParent = ((Fragment) mActivity).getActivity().getWindow().getDecorView().findViewById(android.R.id.content);
        }
        int[] position = new int[2];
        View parent;
        if (topView != null) {
            parent = topView;
        } else {
            parent = getDialog().getWindow().getDecorView().findViewById(android.R.id.content);
        }
        parent.getLocationOnScreen(position);
        contentParent.getLocationOnScreen(offsetPosition);
        baseView.getLocationOnScreen(offsetChildPosition);
        if (offsetPosition[1] > 0 && position[1] == 0) {
            offsetChildPosition[1] = offsetChildPosition[1] - offsetPosition[1];
        } else if (offsetPosition[1] > 0 && position[1] > 0) {
            offsetChildPosition[1] = offsetChildPosition[1] - position[1];
        } else if (offsetPosition[1] == 0 && position[1] > 0) {
            offsetChildPosition[1] = offsetChildPosition[1] + position[1];
        }

        layoutChildPosition(offsetChildPosition);

        //        Log.e("TTTTTTT111",offsetPosition[0]+"||"+offsetPosition[1]+"||"+position[0]+"||"+position[1]+"||"+offsetChildPosition[0]+"||"+offsetChildPosition[1]+"||");

    }

    /**
     * 蒙层需要重写此方法
     *
     * @param offsetChildPosition 处理后的位置信息
     */
    protected void layoutChildPosition(int[] offsetChildPosition) {

    }

    @Override
    public void onResume() {
        super.onResume();

        if (isFristCreate) {
            isFristCreate = false;
            if (mBinding.getRoot() != null) {
                mBinding.getRoot().post(new Runnable() {
                    @Override
                    public void run() {
                        handleRelativePosition();
                    }
                });
            }
        }

        //设置参数
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();

        // 设置动画效果
        if (this.mAnimationsResId > 0) {
            window.setWindowAnimations(this.mAnimationsResId);
        }

        if (isExactSize) {
            lp.width = this.mWidth;
            lp.height = this.mHeight;
        } else {
            //设置宽度
            if (this.dialog_width_ratio > WRAP_CONTENT) {
                setWidthRatio(this.dialog_width_ratio);
                lp.width = this.mWidth;
            } else if (this.dialog_width_ratio == MATCH_PARENT) {
                lp.width = LinearLayout.LayoutParams.MATCH_PARENT;
            } else {
                lp.width = LinearLayout.LayoutParams.WRAP_CONTENT;
            }

            //设置高度
            if (this.dialog_height_ratio > WRAP_CONTENT) {
                setHeightRatio(this.dialog_height_ratio);
                lp.height = this.mHeight;
            } else if (this.dialog_height_ratio == MATCH_PARENT) {
                lp.height = LinearLayout.LayoutParams.MATCH_PARENT;
            } else {
                lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            }
        }
        //设置位置
        if (mGravity != 0) {
            lp.gravity = mGravity;
        }

        if (dimAmount != -1) {
            lp.dimAmount = dimAmount;
        }
        window.setAttributes(lp);
    }

    /**
     * 设置显示位置
     *
     * @param gravity Gravity.BOTTOM  or  Gravity.TOP  or Gravity.CENTER
     */
    public void setGravity(int gravity) {
        this.mGravity = gravity;
    }

    /**
     * 设置运行动画
     *
     * @param animationsResId dialog进入退出动画
     */
    public void settWindowAnimations(int animationsResId) {
        this.mAnimationsResId = animationsResId;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (this.onDismissListener != null) {
            this.onDismissListener.onDismiss(dialog);
        }
        super.onDismiss(dialog);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialog);
        }
        super.onCancel(dialog);
    }

    private boolean isExactSize = false;
    private double dialog_width_ratio;
    private double dialog_height_ratio;

    /**
     * 直接设置页面的宽高，权重大于设置页面屏幕宽高比例的比例
     *
     * @param width  宽度
     * @param height 高度
     */
    public void setSize(int width, int height) {
        isExactSize = true;
        this.mWidth = width;
        this.mHeight = height;
    }

    /**
     * 设置Dialog的屏幕占比
     *
     * @param dialog_width_ratio  屏幕宽度比例
     * @param dialog_height_ratio 屏幕高度比例
     */
    public void setDialogSizeRatio(double dialog_width_ratio, double dialog_height_ratio) {
        this.dialog_width_ratio = dialog_width_ratio;
        this.dialog_height_ratio = dialog_height_ratio;
    }

    /**
     * 设置宽度屏占比
     *
     * @param dialog_width_ratio 屏幕宽度比例
     */
    public void setDialogWidthSizeRatio(double dialog_width_ratio) {
        this.dialog_width_ratio = dialog_width_ratio;
    }

    /**
     * 设置宽度屏占比
     */
    private void setWidthRatio(double dialog_width_ratio) {
        DisplayMetrics dm = getDisplayMetrics();
        mWidth = (int) (dm.widthPixels * dialog_width_ratio);
    }

    /**
     * 设置高度屏占比
     */
    private void setHeightRatio(double dialog_height_ratio) {
        DisplayMetrics dm = getDisplayMetrics();
        mHeight = (int) (dm.heightPixels * dialog_height_ratio);
    }

    /**
     * 获取宽高对象
     */
    private DisplayMetrics getDisplayMetrics() {
        WindowManager windowManager = getActivity().getWindow().getWindowManager();
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    /**
     * 显示dialog
     *
     * @param context FragmentActivity实例
     */
    public void showDialog(FragmentActivity context) {
        try {
            if (!isAdded()) {
                FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                transaction.add(this, context.toString());
                transaction.commitAllowingStateLoss();

            } else {
                Log.d("BaseDialogFragment", "---BaseDialogFragment--->The BaseDialogFragment is already show.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    public void setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
    }

    @Override
    public void dismiss() {
        dismissAllowingStateLoss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}