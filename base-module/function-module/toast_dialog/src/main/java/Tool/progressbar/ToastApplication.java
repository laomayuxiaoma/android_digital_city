package Tool.progressbar;

import android.app.Application;
import android.content.Context;

/**
 * Created by Airr on 2018/9/5.
 */

public class ToastApplication {
    public static Application mApplication;
    public static  void initApplication(Application application){
        mApplication=application;
    }

    public static Context getContext(){
        return mApplication.getApplicationContext();
    }

}
