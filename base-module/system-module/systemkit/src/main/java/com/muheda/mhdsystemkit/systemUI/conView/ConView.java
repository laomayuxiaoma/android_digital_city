package com.muheda.mhdsystemkit.systemUI.conView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by 13660 on 2019/4/22.
 */

public class ConView extends LinearLayout{


    public ConView(Context context) {
        super(context);
    }

    public ConView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ConView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ConView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setData(Context context, List<TagDto> mList){
        try {
            for (TagDto mTagDto:mList){

                Class clazz=mTagDto.getTag();
                Constructor constructor=clazz.getConstructor(Context.class, ViewGroup.class);
                BaseModelView baseModelView= (BaseModelView) constructor.newInstance(context,this);
                Method method2=clazz.getMethod("setData",DataModel.class);
                method2.invoke(baseModelView,new DataModel(mTagDto.getKey(),mTagDto.getValue()));
                Method method=clazz.getMethod("init");
                method.invoke(baseModelView);
                Method method1=clazz.getMethod("setMarign",int.class,int.class);
                method1.invoke(baseModelView,mTagDto.getTop(),mTagDto.getBottom());
                addView(baseModelView.getView());
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
