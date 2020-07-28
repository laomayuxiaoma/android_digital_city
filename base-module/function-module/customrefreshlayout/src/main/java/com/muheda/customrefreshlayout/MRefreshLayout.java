package com.muheda.customrefreshlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.api.ScrollBoundaryDecider;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.lang.reflect.Constructor;

public class MRefreshLayout extends SmartRefreshLayout implements OnMultiPurposeListener {

    public static void init() {
        MRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, R.color.colorPrimaryDark);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        MRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    public MRefreshLayout(Context context) {
        super(context);
    }

    public MRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 启用越界，并设置背景色颜色
     *
     * @param color 设置背景颜色
     */
    public MRefreshLayout setOverScrollBounce(int color) {
        setEnablePureScrollMode(true)
                .setEnableOverScrollDrag(true)
                .setEnableOverScrollBounce(true);
        this.setBackgroundColor(getResources().getColor(color));
        return this;
    }

    @Override
    public MRefreshLayout finishLoadMore() {
        super.finishLoadMore();
        return this;
    }

    @Override
    public MRefreshLayout setFooterHeight(float heightDp) {
        super.setFooterHeight(heightDp);
        return this;
    }

    @Override
    public MRefreshLayout setHeaderHeight(float heightDp) {
        super.setHeaderHeight(heightDp);
        return this;
    }

    @Override
    public MRefreshLayout setHeaderInsetStart(float insetDp) {
        super.setHeaderInsetStart(insetDp);
        return this;
    }

    @Override
    public MRefreshLayout setFooterInsetStart(float insetDp) {
        super.setFooterInsetStart(insetDp);
        return this;
    }

    @Override
    public MRefreshLayout setDragRate(float rate) {
        super.setDragRate(rate);
        return this;
    }

    @Override
    public MRefreshLayout setHeaderMaxDragRate(float rate) {
        super.setHeaderMaxDragRate(rate);
        return this;
    }

    @Override
    public MRefreshLayout setFooterMaxDragRate(float rate) {
        super.setFooterMaxDragRate(rate);
        return this;
    }

    @Override
    public MRefreshLayout setHeaderTriggerRate(float rate) {
        super.setHeaderTriggerRate(rate);
        return this;
    }

    @Override
    public MRefreshLayout setFooterTriggerRate(float rate) {
        super.setFooterTriggerRate(rate);
        return this;
    }

    @Override
    public MRefreshLayout setReboundInterpolator(@NonNull Interpolator interpolator) {
        super.setReboundInterpolator(interpolator);
        return this;
    }

    @Override
    public MRefreshLayout setReboundDuration(int duration) {
        super.setReboundDuration(duration);
        return this;
    }

    @Override
    public MRefreshLayout setEnableLoadMore(boolean enabled) {
        super.setEnableLoadMore(enabled);
        return this;
    }

    @Override
    public MRefreshLayout setEnableRefresh(boolean enabled) {
        super.setEnableRefresh(enabled);
        return this;
    }

    @Override
    public MRefreshLayout setEnableHeaderTranslationContent(boolean enabled) {
        super.setEnableHeaderTranslationContent(enabled);
        return this;
    }

    @Override
    public MRefreshLayout setEnableFooterTranslationContent(boolean enabled) {
        super.setEnableFooterTranslationContent(enabled);
        return this;
    }

    @Override
    public MRefreshLayout setEnableAutoLoadMore(boolean enabled) {
        super.setEnableAutoLoadMore(enabled);
        return this;
    }

    @Override
    public MRefreshLayout setEnableOverScrollBounce(boolean enabled) {
        super.setEnableOverScrollBounce(enabled);
        return this;
    }

    @Override
    public MRefreshLayout setEnablePureScrollMode(boolean enabled) {
        super.setEnablePureScrollMode(enabled);
        return this;
    }

    @Override
    public MRefreshLayout setEnableScrollContentWhenLoaded(boolean enabled) {
        super.setEnableScrollContentWhenLoaded(enabled);
        return this;
    }

    @Override
    public MRefreshLayout setEnableScrollContentWhenRefreshed(boolean enabled) {
        super.setEnableScrollContentWhenRefreshed(enabled);
        return this;
    }

    @Override
    public MRefreshLayout setEnableLoadMoreWhenContentNotFull(boolean enabled) {
        super.setEnableLoadMoreWhenContentNotFull(enabled);
        return this;
    }

    @Override
    public MRefreshLayout setEnableOverScrollDrag(boolean enabled) {
        super.setEnableOverScrollDrag(enabled);
        return this;
    }

    @Override
    public MRefreshLayout setEnableFooterFollowWhenLoadFinished(boolean enabled) {
        super.setEnableFooterFollowWhenLoadFinished(enabled);
        return this;
    }

    @Override
    public MRefreshLayout setEnableClipHeaderWhenFixedBehind(boolean enabled) {
        super.setEnableClipHeaderWhenFixedBehind(enabled);
        return this;
    }

    @Override
    public MRefreshLayout setEnableClipFooterWhenFixedBehind(boolean enabled) {
        super.setEnableClipFooterWhenFixedBehind(enabled);
        return this;
    }

    @Override
    public MRefreshLayout setEnableNestedScroll(boolean enabled) {
        super.setEnableNestedScroll(enabled);
        return this;
    }

    @Override
    public MRefreshLayout setDisableContentWhenRefresh(boolean disable) {
        super.setDisableContentWhenRefresh(disable);
        return this;
    }

    @Override
    public MRefreshLayout setDisableContentWhenLoading(boolean disable) {
        super.setDisableContentWhenLoading(disable);
        return this;
    }

    @Override
    public MRefreshLayout setRefreshHeader(@NonNull RefreshHeader header) {
        super.setRefreshHeader(header);
        return this;
    }

    /**
     * 设置头部
     *
     * @param header
     * @return
     */
    public MRefreshLayout setRefreshHeader(final Class<? extends RefreshHeader> header) {
        try {
            final Constructor<? extends RefreshHeader> declaredConstructor = header.getDeclaredConstructor(Context.class);
            MRefreshLayout.super.setRefreshHeader(declaredConstructor.newInstance(MRefreshLayout.this.getContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public MRefreshLayout setRefreshHeader(@NonNull RefreshHeader header, int width, int height) {
        super.setRefreshHeader(header, width, height);
        return this;
    }

    public MRefreshLayout setRefreshFooter(final Class<? extends RefreshFooter> header) {
        try {
            final Constructor<? extends RefreshFooter> declaredConstructor = header.getDeclaredConstructor(Context.class);
            MRefreshLayout.super.setRefreshFooter(declaredConstructor.newInstance(MRefreshLayout.this.getContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public MRefreshLayout setRefreshFooter(@NonNull RefreshFooter footer) {
        super.setRefreshFooter(footer);
        return this;
    }

    @Override
    public MRefreshLayout setRefreshFooter(@NonNull RefreshFooter footer, int width, int height) {
        super.setRefreshFooter(footer, width, height);
        return this;
    }

    @Override
    public RefreshLayout setRefreshContent(@NonNull View content) {
        super.setRefreshContent(content);
        return this;
    }

    @Override
    public RefreshLayout setRefreshContent(@NonNull View content, int width, int height) {
        super.setRefreshContent(content, width, height);
        return this;
    }

    @Nullable
    @Override
    public RefreshFooter getRefreshFooter() {
        return super.getRefreshFooter();
    }

    @Nullable
    @Override
    public RefreshHeader getRefreshHeader() {
        return super.getRefreshHeader();
    }

    @Override
    public RefreshState getState() {
        return super.getState();
    }

    @Override
    public MRefreshLayout getLayout() {
        return this;
    }

    /**
     * 设置下拉刷新监听屏蔽加载更多
     * @param listener
     * @return
     */
    @Override
    public MRefreshLayout setOnRefreshListener(OnRefreshListener listener) {
        super.setOnRefreshListener(listener);
        initEnableRefreshOrLoadMore();
        return this;
    }

    private void initEnableRefreshOrLoadMore(){
        setEnableLoadMore(true);
        setEnableRefresh(true);
        if (null == mLoadMoreListener){
            setEnableLoadMore(false);
        }
        if (null == mRefreshListener){
            setEnableRefresh(false);
        }
    }

    /**
     * 设置加载更多监听屏蔽下拉刷新
     * @param listener
     * @return
     */
    @Override
    public MRefreshLayout setOnLoadMoreListener(OnLoadMoreListener listener) {
        super.setOnLoadMoreListener(listener);
        initEnableRefreshOrLoadMore();
        return this;
    }

    @Override
    public MRefreshLayout setOnRefreshLoadMoreListener(OnRefreshLoadMoreListener listener) {
        super.setOnRefreshLoadMoreListener(listener);
        initEnableRefreshOrLoadMore();
        return this;
    }

    @Override
    public MRefreshLayout setOnMultiPurposeListener(OnMultiPurposeListener listener) {
        super.setOnMultiPurposeListener(listener);
        return this;
    }

    @Override
    public MRefreshLayout setPrimaryColors(int... primaryColors) {
        super.setPrimaryColors(primaryColors);
        return this;
    }

    @Override
    public MRefreshLayout setPrimaryColorsId(int... primaryColorId) {
        super.setPrimaryColorsId(primaryColorId);
        return this;
    }

    @Override
    public MRefreshLayout setScrollBoundaryDecider(ScrollBoundaryDecider boundary) {
        super.setScrollBoundaryDecider(boundary);
        return this;
    }

    @Override
    public MRefreshLayout setNoMoreData(boolean noMoreData) {
        super.setNoMoreData(noMoreData);
        return this;
    }

    @Override
    public MRefreshLayout finishRefresh() {
        super.finishRefresh();
        return this;
    }

    @Override
    public MRefreshLayout finishRefresh(int delayed) {
        super.finishRefresh(delayed);
        return this;
    }

    @Override
    public MRefreshLayout finishRefresh(boolean success) {
        super.finishRefresh(success);
        return this;
    }

    @Override
    public MRefreshLayout finishLoadMore(int delayed) {
        super.finishLoadMore(delayed);
        return this;
    }

    @Override
    public MRefreshLayout finishLoadMore(boolean success) {
        super.finishLoadMore(success);
        return this;
    }

    @Override
    public MRefreshLayout finishLoadMore(int delayed, boolean success, boolean noMoreData) {
        super.finishLoadMore(delayed, success, noMoreData);
        return this;
    }

    public MRefreshLayout finishLoadMoreWithNoMoreData(boolean isFinishHideFooter) {
        if (isFinishHideFooter) {
            super.setEnableFooterFollowWhenLoadFinished(true);
            super.finishLoadMoreWithNoMoreData();
        } else {
            super.finishLoadMoreWithNoMoreData();
        }
        return this;
    }

    @Override
    public RefreshLayout closeHeaderOrFooter() {
        super.closeHeaderOrFooter();
        return this;
    }

    @Override
    public boolean autoRefresh() {
        return super.autoRefresh();
    }

    @Override
    public boolean autoRefresh(int delayed) {
        return super.autoRefresh(delayed);
    }

    @Override
    public boolean autoLoadMore() {
        return super.autoLoadMore();
    }

    @Override
    public boolean post(@NonNull Runnable action) {
        return super.post(action);
    }

    @Override
    public boolean postDelayed(@NonNull Runnable action, long delayMillis) {
        return super.postDelayed(action, delayMillis);
    }

    @Override
    public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {

    }

    @Override
    public void onHeaderReleased(RefreshHeader header, int headerHeight, int maxDragHeight) {

    }

    @Override
    public void onHeaderStartAnimator(RefreshHeader header, int headerHeight, int maxDragHeight) {

    }

    @Override
    public void onHeaderFinish(RefreshHeader header, boolean success) {

    }

    @Override
    public void onFooterMoving(RefreshFooter footer, boolean isDragging, float percent, int offset, int footerHeight, int maxDragHeight) {

    }

    @Override
    public void onFooterReleased(RefreshFooter footer, int footerHeight, int maxDragHeight) {

    }

    @Override
    public void onFooterStartAnimator(RefreshFooter footer, int footerHeight, int maxDragHeight) {

    }

    @Override
    public void onFooterFinish(RefreshFooter footer, boolean success) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {

    }


//    @Override
//    public boolean dispatchTouchEvent(MotionEvent e) {
//        if (e.getAction() == MotionEvent.ACTION_DOWN){
//            super.dispatchTouchEvent(e);
//            return false;
//        }
//        return super.dispatchTouchEvent(e);
//    }
}
