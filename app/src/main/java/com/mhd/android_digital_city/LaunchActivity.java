package com.mhd.android_digital_city;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mhd.basekit.viewkit.util.route.RouteConstant;
import com.mhd.basekit.viewkit.util.route.RouteUtil;


public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
//        RouteUtil.routeSkip(RouteConstant.me_loginActivity, new String[][]{});
        RouteUtil.routeSkip(RouteConstant.main_activity, new String[][]{});
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                LaunchActivity.this.finish();
            }
        },1600);
    }
}
