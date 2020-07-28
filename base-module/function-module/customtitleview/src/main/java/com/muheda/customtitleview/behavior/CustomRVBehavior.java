package com.muheda.customtitleview.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public abstract class CustomRVBehavior extends CoordinatorLayout.Behavior {

    /**
     * 要变化的view的id列表
     */
    public int[] viewIds;
    /**
     * 要变化的view集合
     */
    private View[] views;

    public CustomRVBehavior() {
        super();
    }

    public CustomRVBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        boolean isDependency = getLayoutDependsOn(parent, child, dependency);
        return isDependency == true ? true : super.layoutDependsOn(parent, child, dependency);
    }

    public abstract boolean getLayoutDependsOn(CoordinatorLayout parent, View child, View dependency);

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if (null != viewIds && null == views) {
            getViews(child);
        }
        boolean dependentViewChanged = onHandleDependentViewChanged(parent, child, dependency, views);
        return dependentViewChanged == true ? true : super.onDependentViewChanged(parent, child, dependency);
    }

    public abstract boolean onHandleDependentViewChanged(CoordinatorLayout parent, View child, View dependency, View[] views);

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        if (null != viewIds && null == views) {
            getViews(child);
        }
        return onHandleStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type, views);
    }

    public abstract boolean onHandleStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type, View[] views);

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        if (null != viewIds && null == views) {
            getViews(child);
        }
        onHandleNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type, views);
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
    }

    public abstract void onHandleNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type, View[] views);

    @Override
    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int type) {
        super.onStopNestedScroll(coordinatorLayout, child, target, type);
        onHandleStopNestedScroll(coordinatorLayout, child, target, type,views);
    }

    public abstract void onHandleStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int type, View[] views);

    /**
     * 获取view集合
     * @param view
     */
    private void getViews(View view) {
        if (null == viewIds) {
            return;
        }
        if (null == views) {
            views = new View[viewIds.length];
        }
        for (int i = 0; i < viewIds.length; i++) {
            views[i] = view.findViewById(viewIds[i]);
        }
    }
}
