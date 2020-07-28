package com.mhd.basekit.viewkit.view.dialog;

import android.util.Log;
import android.view.Gravity;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.mhd.basekit.R;
import com.mhd.basekit.databinding.DialogCalendarBinding;
import com.muheda.mhdsystemkit.systemUI.dialog.BaseDialogFragment;

/**
 * @author zhangming
 * @Date 2019/7/4 13:21
 * @Description: 日历dialog
 */
public class CalendarDialog extends BaseDialogFragment<DialogCalendarBinding> implements CalendarView.OnCalendarSelectListener,
        CalendarView.OnYearChangeListener {

    public CalendarDialog() {
        settWindowAnimations(R.style.AnimDownInDownOutOverShoot);
        setGravity(Gravity.BOTTOM);
        setDialogSizeRatio(MATCH_PARENT, WRAP_CONTENT);
        setCancelable(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_calendar;
    }

    @Override
    protected void initView() {
        mBinding.calendarView.setOnCalendarSelectListener(this);
        mBinding.calendarView.setOnYearChangeListener(this);
    }

    @Override
    protected void init() {
//        int year = mBinding.calendarView.getCurYear();
//        int month = mBinding.calendarView.getCurMonth();
//
//        Map<String, Calendar> map = new HashMap<>();
//        map.put(getSchemeCalendar(year, month, 3, 0xFF40db25, "假").toString(),
//                getSchemeCalendar(year, month, 3, 0xFF40db25, "假"));
//        map.put(getSchemeCalendar(year, month, 6, 0xFFe69138, "事").toString(),
//                getSchemeCalendar(year, month, 6, 0xFFe69138, "事"));
//        map.put(getSchemeCalendar(year, month, 9, 0xFFdf1356, "议").toString(),
//                getSchemeCalendar(year, month, 9, 0xFFdf1356, "议"));
//        map.put(getSchemeCalendar(year, month, 13, 0xFFedc56d, "记").toString(),
//                getSchemeCalendar(year, month, 13, 0xFFedc56d, "记"));
//        map.put(getSchemeCalendar(year, month, 14, 0xFFedc56d, "记").toString(),
//                getSchemeCalendar(year, month, 14, 0xFFedc56d, "记"));
//        map.put(getSchemeCalendar(year, month, 15, 0xFFaacc44, "假").toString(),
//                getSchemeCalendar(year, month, 15, 0xFFaacc44, "假"));
//        map.put(getSchemeCalendar(year, month, 18, 0xFFbc13f0, "记").toString(),
//                getSchemeCalendar(year, month, 18, 0xFFbc13f0, "记"));
//        map.put(getSchemeCalendar(year, month, 25, 0xFF13acf0, "假").toString(),
//                getSchemeCalendar(year, month, 25, 0xFF13acf0, "假"));
//        map.put(getSchemeCalendar(year, month, 27, 0xFF13acf0, "多").toString(),
//                getSchemeCalendar(year, month, 27, 0xFF13acf0, "多"));
        //此方法在巨大的数据量上不影响遍历性能，推荐使用
//        mBinding.calendarView.setSchemeDate(map);
    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        calendar.addScheme(new Calendar.Scheme());
        calendar.addScheme(0xFF008800, "假");
        calendar.addScheme(0xFF008800, "节");
        return calendar;
    }

    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        Log.e("TTTTTTTT", calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay());
    }

    @Override
    public void onYearChange(int year) {

    }
}
