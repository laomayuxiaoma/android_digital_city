package com.example.muheda.functionkit.etchangekit;

import android.widget.EditText;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.jakewharton.rxbinding2.widget.RxTextView;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by 13660 on 2018/10/9.
 */

public class EtChangeUtil implements LifecycleObserver {
    private static Disposable disposable;
    //判断是否输入
    public static void etSingleChange(EditText editText, final ChangeListener<String> changeListener ){
        etSingleChange(editText,1,changeListener);
    }

    /**
     * @param editText et
     * @param skipCount 监听的位置
     * @param changeListener 回调
     */
    public static void etSingleChange(EditText editText, final int skipCount, final ChangeListener<String> changeListener ){
        try {
            ((FragmentActivity)editText.getContext()).getLifecycle().addObserver(EtChangeUtil.class.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        disposable=RxTextView.textChanges(editText)
                .skip(skipCount)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        if (charSequence.length()>=skipCount){
                            changeListener.onSkip(charSequence.toString());
                        }else{
                            changeListener.onNoSkip(changeListener.toString());
                        }
                    }
                });

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public static void onDestory(){
        if (disposable!=null){
            disposable.dispose();
            disposable=null;
        }

    }
}
