package com.ldf.calendar.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.ldf.calendar.CalendarUtils;
import com.ldf.calendar.view.MonthPager;

public class RecyclerViewBehavior extends CoordinatorLayout.Behavior<RecyclerView> {
    private int initOffset = -1;
    private int minOffset = -1;
    private Context context;
    private boolean initiated = false;

    public RecyclerViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, RecyclerView child, int layoutDirection) {
        parent.onLayoutChild(child, layoutDirection);
        MonthPager monthPager = getMonthPager(parent);
        initMinOffsetAndInitOffset(parent, child, monthPager);
        return true;
    }

    private void initMinOffsetAndInitOffset(CoordinatorLayout parent,
                                            RecyclerView child,
                                            MonthPager monthPager) {
        if (monthPager.getBottom() > 0 && initOffset == -1) {
            initOffset = monthPager.getViewHeight();
            saveTop(initOffset);
        }
        if(!initiated) {
            initOffset = monthPager.getViewHeight();
            saveTop(initOffset);
            initiated = true;
        }
        child.offsetTopAndBottom(CalendarUtils.loadTop());
        minOffset = getMonthPager(parent).getCellHeight();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, RecyclerView child,
                                       View directTargetChild, View target, int nestedScrollAxes) {
        Log.e("ldf","onStartNestedScroll");
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) child.getLayoutManager();
        if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() > 0) {
            return false;
        }
        MonthPager monthPager = (MonthPager) coordinatorLayout.getChildAt(0);
        if (monthPager.getPageScrollState() != ViewPager.SCROLL_STATE_IDLE) {
            return false;
        }
        monthPager.setScrollable(false);
        boolean isVertical = (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
        int firstRowVerticalPosition =
                (child == null || child.getChildCount() == 0) ? 0 : child.getChildAt(0).getTop();
        boolean recycleviewTopStatus = firstRowVerticalPosition >= 0;
        return isVertical
                && (recycleviewTopStatus || !CalendarUtils.isScrollToBottom())
                && child == directTargetChild;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, RecyclerView child,
                                  View target, int dx, int dy, int[] consumed) {
        Log.e("ldf","onNestedPreScroll");
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        if (child.getTop() <= initOffset
                && child.getTop() >= getMonthPager(coordinatorLayout).getCellHeight()) {
            consumed[1] = CalendarUtils.scroll(child, dy,
                    getMonthPager(coordinatorLayout).getCellHeight(),
                    getMonthPager(coordinatorLayout).getViewHeight());
            saveTop(child.getTop());
        }
    }

    @Override
    public void onStopNestedScroll(final CoordinatorLayout parent, final RecyclerView child, View target) {
        Log.e("ldf","onStopNestedScroll");
        super.onStopNestedScroll(parent, child, target);
        MonthPager monthPager = (MonthPager) parent.getChildAt(0);
        monthPager.setScrollable(true);
        if (!CalendarUtils.isScrollToBottom()) {
            if (initOffset - CalendarUtils.loadTop() > CalendarUtils.getTouchSlop(context)) {
                CalendarUtils.scrollTo(parent, child, getMonthPager(parent).getCellHeight(), 200);
            } else {
                CalendarUtils.scrollTo(parent, child, getMonthPager(parent).getViewHeight(), 80);
            }
        } else {
            if (CalendarUtils.loadTop() - minOffset > CalendarUtils.getTouchSlop(context)) {
                CalendarUtils.scrollTo(parent, child, getMonthPager(parent).getViewHeight(), 200);
            } else {
                CalendarUtils.scrollTo(parent, child, getMonthPager(parent).getCellHeight(), 80);
            }
        }
    }

    private MonthPager getMonthPager(CoordinatorLayout coordinatorLayout) {
        MonthPager monthPager = (MonthPager) coordinatorLayout.getChildAt(0);
        return monthPager;
    }

    private void saveTop(int top) {
        CalendarUtils.saveTop(top);
        if (CalendarUtils.loadTop() == initOffset) {
            CalendarUtils.setScrollToBottom(false);
        } else if (CalendarUtils.loadTop() == minOffset) {
            CalendarUtils.setScrollToBottom(true);
        }
    }
}
