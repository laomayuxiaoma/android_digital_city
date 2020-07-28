package Tool;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toast_dialog.R;

import static android.widget.Toast.makeText;


/**
 * Created by wangfei
 */
public class ToastUtils {

    private static PopupWindow popupWindow;

    private ToastUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static final int SHOW_TOAST = 0;
    private static final int SHOW_POP = 1;

    //    private static Context mContext;
    @SuppressLint("HandlerLeak")
    private static Handler baseHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_POP:
                    popupWindow.dismiss();
                    break;

                default:
                    break;
            }
        }
    };


    /**可自定义布局 时长 显示位置 坐标
     * @param context
     * @param message
     * @param layout
     * @param duration
     * @param xOffset
     * @param yOffset
     * @param gravity
     * @return
     */
    public static Toast makeToast(Context context,String message,int layout,int duration,int xOffset,int yOffset,int gravity){
        return makeToast(context, message, layout,gravity,duration,xOffset,yOffset,null);
    }

    /**可自定义布局 时长 显示位置
     * @param context
     * @param message
     * @param layout
     * @param duration
     * @param gravity
     * @return
     */
    public static Toast makeToast(Context context,String message,int layout,int duration,int gravity){
        return makeToast(context, message, layout,gravity,duration,0,0,null);
    }

    /**可定义显示位置 时长
     * @param context
     * @param message
     * @param duration
     * @param gravity
     * @return
     */
    public static Toast makeToast(Context context, String message, int duration, int gravity){
        return makeToast(context,message,0,gravity,duration,0,0,null);
    }

    /**Toast 长时间显示
     * @param context
     * @param message
     */
    public static void showLong(Context context,String message){

        makeToast(context,message,Toast.LENGTH_LONG).show();
    }

    /**Toast 短时间显示
     * @param context
     * @param message
     */
    public static void showShort(Context context,String message){

        makeToast(context,message,Toast.LENGTH_SHORT).show();
    }


    /**常规 Toast 可定义显示时长 默认位置
     * @param context
     * @param message
     * @param duration
     * @return
     */
        public static Toast makeToast(Context context,String message,int duration){

            return makeToast(context,message,0,0,duration,0,0,null);
        }

    /**
     * @param context
     * @param message
     * @param layout 布局
     * @param gravity 位置
     * @param duration 时间
     * @param
     * @return
     */
    public static Toast makeToast(Context context, final String message, int layout, int gravity, int duration, int xOffset,int yOffset,final IToastListener<String> iToastListener) {
        final Toast commonToast = makeText(context, "", duration);
        if (layout == 0) {
            commonToast.setText(message);
            if (gravity != 0) {
                commonToast.setGravity(gravity, 0, 0);
            }
            return commonToast;
        }

        final View toastView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(layout, null);
        TextView toastText = toastView.findViewWithTag("text_toast");
        toastText.setText(message);
        if (iToastListener!=null){
            View view=toastView.findViewWithTag("click_toast");
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iToastListener.toastListener(message);
                }
            });
        }
        commonToast.setGravity(gravity, xOffset, yOffset);
        commonToast.setView(toastView);
        return commonToast;

    }





    public static void showPop(Context context, int layout) {
        try {
            View view=LayoutInflater.from(context).inflate(layout,null);
            popupWindow = new PopupWindow(context);
            popupWindow.setContentView(view);
            popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(false);
            popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);

            popupWindow.showAtLocation(((Activity)context).getWindow().getDecorView(), Gravity.TOP, 0, 0);
            baseHandler.sendEmptyMessageDelayed(SHOW_POP, 3000);
        }catch(Exception e){
            throw new UnsupportedOperationException("Activity 未创建完成");
        }

    }

    public static DialogUtils showDialog(){
        return DialogUtils.getInstance();
    }


}
