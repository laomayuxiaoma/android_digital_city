package com.mhd.basekit.viewkit.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.mhd.basekit.R;
import com.mhd.basekit.viewkit.mvp.view.netstateview.Loading;
import com.mhd.basekit.viewkit.mvp.view.netstateview.NetError;
import com.mhd.basekit.viewkit.mvp.view.netstateview.NoData;
import com.mhd.basekit.viewkit.view.BaseDBFragment;
import com.muheda.mhdsystemkit.systemUI.stateView.StateView;

import java.util.HashMap;

public class GlobalLoadDialogShow<T> implements BaseDialogShow {

    public static final int NET_STATE_DISMISS = 1;
    public static final int NET_STATE_NO_DATA = 2;
    public static final int NET_STATE_ERROR = 4;

    public static final String NET_LOADING = "NET_LOADING";
    public static final String NET_NO_DATA = "NET_NO_DATA";
    public static final String NET_ERROR = "NET_ERROR";
    private static HashMap<String, Class> stateMapConfig = new HashMap<>();

    static {
        stateMapConfig.put(NET_LOADING, Loading.class);
        stateMapConfig.put(NET_NO_DATA, NoData.class);
        stateMapConfig.put(NET_ERROR, NetError.class);
    }

    private View root;
    private StateView svDlg;
    private HashMap<String, Class> mMapConfig;
    private T t;

    public GlobalLoadDialogShow init(T t) {
        this.t = t;
        Activity activity = null;
        if (t instanceof FragmentActivity) {
            activity = (FragmentActivity) t;
            svDlg = activity.findViewById(R.id.ll_load_page);
        } else if (t instanceof BaseDBFragment) {
            svDlg = ((BaseDBFragment) t).getmBinding().getRoot().findViewById(R.id.ll_load_page);
        }
        initStateMapConfig();
        return this;
    }

    @Override
    public void show(Context mContext) {
        if (svDlg == null) {
            return;
        }
        svDlg.setVisibility(View.VISIBLE);
        svDlg.setData(NET_LOADING, stateMapConfig);
    }

    @Override
    public void show(Context mContext, String message) {

    }

    @Override
    public boolean isShowing() {
        if (svDlg == null) {
            return false;
        }
        return svDlg.getVisibility() == View.VISIBLE && NET_LOADING.equals(svDlg.getTag());
    }

    @Override
    public void dismiss(int type) {
        if (svDlg == null) {
            return;
        }
        svDlg.setVisibility(View.VISIBLE);
        switch (type) {
            case NET_STATE_DISMISS:
                svDlg.setVisibility(View.GONE);
                break;
            case NET_STATE_NO_DATA:
                svDlg.setData(NET_NO_DATA, stateMapConfig);
                break;
            case NET_STATE_ERROR:
                svDlg.setData(NET_ERROR, stateMapConfig, t);
                break;
        }
    }


    protected void initStateMapConfig() {
        mMapConfig = stateMapConfig;
    }

    public HashMap getMMapConfig(){
        return mMapConfig;
    }
}
