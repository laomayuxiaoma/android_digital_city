package com.mhd.basekit.config;


import com.mhd.basekit.viewkit.mvp.contract.IMvpView;

/**
 * Created by 13660 on 2018/11/5.
 */

public interface IBaseView<T>  extends IMvpView {
   void resetView(T t);
}
