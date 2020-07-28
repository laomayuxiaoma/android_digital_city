package com.example.muheda.functionkit.netkit.params;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by 13660 on 2018/10/22.
 */

public class ParamsCheck {

    private final int CHECK_DEFAULT = 0;
    private final int CHECK_INIT = 2;
    private final int CHECK_CHECK = 3;

    private StringBuffer errorMessage;
    private boolean isPass = true;
    private String values;
    private int checkState = CHECK_DEFAULT ;
    public ParamsCheck(){
        errorMessage=new StringBuffer();
    }

    /**
     * @param values 校验参数
     * @return
     */
    public ParamsCheck with(String values){
        checkState = CHECK_INIT;
        this.values=values;
        return this;
    }

    /**
     * @param checkValues 比对参数是否相等
     * @param error 错误信息
     * @return
     */
    public ParamsCheck equals(String checkValues,String error){
        checkInit();
        if (checkValues.equals(values)){

        }else{
            errorMessage(error);
        }
        return this;
    }

    public ParamsCheck isNull(String error){
        checkInit();
        if (TextUtils.isEmpty(values)){
            errorMessage(error);
        }
        return this;
    }

    public ParamsCheck betweenLength(int min,int max,String error){
        checkInit();
        if (TextUtils.isEmpty(values)){
            isNull(error);
            return this;
        }
        if (values.length()>min&&values.length()<max){

        }else{
            errorMessage(error);
        }
        return this;
    }

    public void check(String error){
        checkInit();
       if ("true".equals(values)){

       }else {
           errorMessage(error);
       }
    }

    private void checkInit(){
        checkState = CHECK_CHECK;
        if (checkState == CHECK_DEFAULT){
            throw new IllegalArgumentException("校验前需调用with（）方法。");
        }
    }

    private void isChecked(){
        if (checkState == CHECK_INIT){
            throw new IllegalArgumentException("没有调用任何校验方法。");
        }
    }

    public void errorMessage(String error){
        errorMessage.append(error+"\n");
        isPass=false;
    }

    public boolean isPass(Context context){
        isChecked();
        if (!TextUtils.isEmpty(errorMessage.toString())){
            Toast.makeText(context,errorMessage.toString(),Toast.LENGTH_SHORT).show();
        }

        return isPass;
    }
}
