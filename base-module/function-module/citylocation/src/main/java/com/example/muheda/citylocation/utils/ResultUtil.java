package com.example.muheda.citylocation.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by 13660 on 2019/2/18.
 */

public class ResultUtil {
    public static String activityAction;
    public static String packageName;
    public static void resultBundle(Context context,String city,int resultCode){
        Intent intent=new Intent(activityAction);
        Bundle bundle =new Bundle();
        bundle.putString("city",city);
        intent.putExtras(bundle);
        ((Activity)context).setResult(resultCode,intent);
        ((Activity) context).finish();
    }



}
