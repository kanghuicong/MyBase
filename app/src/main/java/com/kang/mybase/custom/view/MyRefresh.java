package com.kang.mybase.custom.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kang.mybase.R;

/**
 * Created by KangHuiCong on 2017/12/25.
 * E-Mail is 515849594@qq.com
 */

public class MyRefresh extends LinearLayout {
    int mHeaderHeight;
    int mFooterHeight;
    int startY;

    int mHeaderState = STOP;
    int mFooterState = STOP;
    int mPullState;
    boolean isRepeat = false;
    boolean isNoLoad = false;
    boolean isLoading = false;

    private final static int STOP = 0;
    private final static int ING = 1;
    private final static int IS_FOOTER = 2;
    private final static int IS_HEADER = 3;
    private final static int IS_NOTHING = 4;
    private final static int HEADER_TOP_ANIMATOR_TIME = 300;
    private final static int HEADER_FOOTER_ANIMATOR_TIME = 500;
    private final static int ALL_ANIMATOR_TIME = 1000;

    private final static float RESILIENCE_FACTOR = 0.3f;

    private final static String REFRESH_1 = "下拉刷新";
    private final static String REFRESH_2 = "松手立即刷新";
    private final static String REFRESH_3 = "正在刷新数据";
    private final static String REFRESH_FAILURE = "刷新失败";
    private final static String REFRESH_SUCCESS = "刷新成功";

    private final static String LOAD_1 = "上拉加载";
    private final static String LOAD_2 = "松手立即加载";
    private final static String LOAD_3 = "正在加载数据";
    private final static String LOAD_FAILURE = "加载失败";
    private final static String LOAD_SUCCESS = "加载成功";
    private final static String LOAD_NOTHING = "没有更多数据了";

    Context context;
    View headerView;
    AdapterView<?> listerView;
    ScrollView scrollView;
    View footerView;

    TextView tvHeader;
    TextView tvFooter;
    ImageView ivHeaderState;
    ImageView ivFooterState;
    MyLoading ivHeaderLoading;
    MyLoading ivFooterLoading;

    IListerRefresh iListerRefresh;

    ValueAnimator headerAnimator;
    ValueAnimator headerTopAnimator;

    public MyRefresh(Context context) {
        this(context, null);
    }

    public MyRefresh(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRefresh(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        this.setOrientation(VERTICAL);

        /*添加header*/
        headerView = View.inflate(context, R.layout.refresh_header, null);
        tvHeader = (TextView) headerView.findViewById(R.id.tv_header);
        ivHeaderState = (ImageView) headerView.findViewById(R.id.iv_state);
        ivHeaderLoading = (MyLoading) headerView.findViewById(R.id.iv_loading);

        measureView(headerView);
        mHeaderHeight = headerView.getMeasuredHeight();
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, mHeaderHeight);
        params.topMargin = -(mHeaderHeight);
        addView(headerView, params);

        /*headerView回弹动画*/
        headerAnimator = ValueAnimator.ofInt(0, -mHeaderHeight);
        headerAnimator.setDuration(HEADER_TOP_ANIMATOR_TIME);
        headerAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                changeHeaderTopMargin((int) animation.getAnimatedValue(), false);
                if ((int) animation.getAnimatedValue() == (-mHeaderHeight)) {
                    if (ivHeaderState.getVisibility() == VISIBLE) ivHeaderState.setVisibility(GONE);
                    if (ivFooterState.getVisibility() == VISIBLE) ivFooterState.setVisibility(GONE);
                    tvHeader.setText(REFRESH_1);
                    if (!isNoLoad) tvFooter.setText(LOAD_1);
                    mHeaderState = STOP;
                    mFooterState = STOP;
                }
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        /*加载完布局后，添加footer*/
        footerView = View.inflate(context, R.layout.refresh_footer, null);
        tvFooter = (TextView) footerView.findViewById(R.id.tv_footer);
        ivFooterState = (ImageView) footerView.findViewById(R.id.iv_state);
        ivFooterLoading = (MyLoading) footerView.findViewById(R.id.iv_loading);

        measureView(footerView);
        mFooterHeight = footerView.getMeasuredHeight();
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, mFooterHeight);
        addView(footerView, params);

        /*获取AbsListView/ScrollView，目前设计的布局限制为header,AbsListView/ScrollView,footer*/
        if (getChildAt(1) instanceof AbsListView) {
            listerView = (AdapterView<?>) getChildAt(1);
        } else if (getChildAt(1) instanceof ScrollView) {
            scrollView = (ScrollView) getChildAt(1);
        }
    }

    private int mStartY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int y = (int) e.getRawY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isHighest = false;
                startY = y;
                mStartY = y;
                startTime = System.currentTimeMillis();

                /*快速下拉/上拉*/
                if ((startTime - finishTime) >= ALL_ANIMATOR_TIME) isRepeat = false;
                else isRepeat = true;

                break;
            case MotionEvent.ACTION_MOVE:
                /*header-->大于0,footer-->小于0*/
                int deltaY = y - startY;
                /*正在加载时下拉/正在刷新时上拉*/
                /*此时的滑动事件是分发给listView，即加载时listView可以滑动*/
                if (deltaY > 0 && mFooterState == ING) {
                    changeHeaderTopMargin(-mFooterHeight, false);
                    removeRunnable(runnableLoad);
                }
                if (deltaY < 0 && mHeaderState == ING) {
                    changeHeaderTopMargin(-mHeaderHeight, false);
                    removeRunnable(runnableRefresh);
                }
                /*判断是否到了头部/底部*/
                if (isRefreshViewScroll(deltaY)) return true;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return false;
    }

    int topMargin = 0;
    private long finishTime = 0;
    private boolean isHighest = false;
    private int highest;
    long startTime;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaY = y - startY;
                startY = y;
                /*下拉X距离紧接着上拉X+Y距离，此时会将footerView拉出*/
                if (y <= mStartY && mPullState == IS_HEADER) {
                    removeRunnable(runnableRefresh);
                    mPullState = IS_NOTHING;
                    return false;
                }
                /*上拉X+Y距离紧接着下拉Y距离，此时会将footerView拉出*/
                /*Y-->mFooterHeight*/
                if (mPullState == IS_FOOTER && deltaY > 0) {
                    if (!isHighest) {
                        highest = startY;
                        isHighest = true;
                    }
                    /*上拉加载是有0.3f回弹系数，所以高度计算需要*.03f*/
                    if (isHighest && (y - highest) * RESILIENCE_FACTOR >= mFooterHeight) {
                        removeRunnable(runnableLoad);
                        mPullState = IS_NOTHING;
                        return false;
                    }
                }

                /*已经到了header*/
                if (mPullState == IS_HEADER && mFooterState == STOP) {
                    topMargin = changeHeaderTopMargin(deltaY, true);
                    /*header完全展示*/
                    if (topMargin > 0) {
                        tvHeader.setText(REFRESH_2);
                        stopLoading(ivHeaderLoading);
                        ivHeaderState.setVisibility(GONE);
                        mHeaderState = ING;
                    }
                } else if (mPullState == IS_FOOTER && mHeaderState == STOP) {
                    int bottomMargin = changeHeaderTopMargin(deltaY, true);
                    if (!isNoLoad && (Math.abs(bottomMargin) >= (mHeaderHeight + mFooterHeight))) {
                        tvFooter.setText(LOAD_2);
                        stopLoading(ivFooterLoading);
                        ivFooterState.setVisibility(GONE);
                        mFooterState = ING;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                finishTime = System.currentTimeMillis();

                if (mPullState == IS_HEADER) {
                    if (mHeaderState == ING) {
                        tvHeader.setText(REFRESH_3);
                        if (!isLoading) startLoading(ivHeaderLoading);
                        mHeaderState = IS_NOTHING;
                    }

                    if (isRepeat) {/*没有执行stopAll方法时快速重复下拉*/
                        headerTopAnimator.end();
                        headerTopAnimator.cancel();
                        headerTopAnimator = null;
                    }
                    startTopAnimator();

                } else if (mPullState == IS_FOOTER) {
                    if (mFooterState == ING) {
                        tvFooter.setText(LOAD_3);
                        if (!isLoading) startLoading(ivFooterLoading);

                        if(isRepeat)removeRunnable(runnableLoad);

                        changeHeaderTopMargin(-(mHeaderHeight + mFooterHeight), false);
                        doHandler(runnableLoad);
                    } else stopAll();
                } else if (isNoLoad) {/*scrollView上拉回弹*/
                    changeHeaderTopMargin(-mHeaderHeight + mFooterHeight, false);
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void startTopAnimator() {
         /*headerView顶部空白回弹动画*/
        headerTopAnimator = ValueAnimator.ofInt(topMargin, 0);
        headerTopAnimator.setDuration(HEADER_TOP_ANIMATOR_TIME);
        headerTopAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                changeHeaderTopMargin((int) animation.getAnimatedValue(), false);
                if ((int) animation.getAnimatedValue() == (0)) {
                    doHandler(runnableRefresh);
                }
            }
        });
        headerTopAnimator.start();
    }

    /*measure子View*/
    private void measureView(View child) {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null)
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;

        if (lpHeight > 0)
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
        else childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);

        child.measure(childWidthSpec, childHeightSpec);
    }

    /*HeaderView归位*/
    private int changeHeaderTopMargin(int deltaY, boolean isSpringBack) {
        LayoutParams params = (LayoutParams) headerView.getLayoutParams();

        if (isSpringBack) {
            float newTopMargin = params.topMargin + deltaY * RESILIENCE_FACTOR;
            if (-newTopMargin >= mHeaderHeight + mFooterHeight) {
                params.topMargin = -(mHeaderHeight + mFooterHeight);
            } else params.topMargin = (int) newTopMargin;
        } else params.topMargin = deltaY;

        headerView.setLayoutParams(params);
        invalidate();
        return params.topMargin;
    }

    private boolean isRefreshViewScroll(int deltaY) {
        if (listerView != null) {
            View child = listerView.getChildAt(0);
            /*没有数据*/
            if (child == null) {
                if (deltaY > 0) {
                    tvHeader.setText(REFRESH_1);
                    mPullState = IS_HEADER;
                    return true;
                } else return false;
            }
            /*item(ListView/GridView)滑动到最顶端*/
            if (deltaY > 0 && child.getTop() == 0) {
                tvHeader.setText(REFRESH_1);
                mPullState = IS_HEADER;
                return true;
            } else if (deltaY < 0) {

                View lastChild = listerView.getChildAt(listerView.getChildCount() - 1);
                if (lastChild == null) return false;
                /*item(ListView/GridView)滑动到最底部*/
                /*且当listView的item不足以铺满所有空间时不可以上拉*/
                if ((lastChild.getBottom() == getHeight()) && (listerView.getLastVisiblePosition() == (listerView.getAdapter().getCount() - 1))) {
                    tvFooter.setText(LOAD_1);
                    mPullState = IS_FOOTER;
                    return true;
                }
            }
        } else if (scrollView != null) {
            ViewGroup layoutChild = (ViewGroup) scrollView.getChildAt(0);
            View child = layoutChild.getChildAt(0);

            if (deltaY > 0 && getScrollY() == 0) {
                tvHeader.setText(REFRESH_1);
                mPullState = IS_HEADER;
                return true;
            } else if (deltaY < 0) {
                if (child == null) return false;

                View lastChild = scrollView.getChildAt(scrollView.getChildCount() - 1);
                if (lastChild == null) return false;
                if (lastChild.getBottom() <= getHeight()) {
                    if (!isNoLoad) tvFooter.setText(LOAD_1);
                    mPullState = IS_FOOTER;
                    return true;
                }
            }
        }
        return false;
    }

    private Runnable runnableRefresh = new Runnable() {
        public void run() {
            if (iListerRefresh != null) iListerRefresh.Refresh();
            else setRefreshState(1);
        }
    };

    private Runnable runnableLoad = new Runnable() {
        public void run() {
            if (iListerRefresh != null) iListerRefresh.Load();
            else setLoadState(1);
        }
    };

    private void doHandler(Runnable runnable) {
        /*ING时，重复下拉/上拉，移除当前的runnable*/
        if (isRepeat) removeRunnable(runnable);

        isRepeat = true;
        postDelayed(runnable, HEADER_FOOTER_ANIMATOR_TIME);
    }

    private void removeRunnable(Runnable runnable) {
        if (runnable != null) removeCallbacks(runnable);
    }

    /*停止所有活动，View归位*/
    private void stopAll() {
        changeHeaderTopMargin(-mHeaderHeight, false);
        if (ivHeaderState.getVisibility() == VISIBLE) ivHeaderState.setVisibility(GONE);
        if (ivFooterState.getVisibility() == VISIBLE) ivFooterState.setVisibility(GONE);
        tvHeader.setText(REFRESH_1);
        if (!isNoLoad) tvFooter.setText(LOAD_1);
        mHeaderState = STOP;
        mFooterState = STOP;
        isRepeat = false;
    }

    /*开始loading动画*/
    private void startLoading(MyLoading myLoading) {
        isLoading = true;
        myLoading.startAnimator();
        myLoading.setVisibility(VISIBLE);
    }

    /*停止loading动画*/
    private void stopLoading(MyLoading myLoading) {
        isLoading = false;
        if (myLoading.isRunning()) myLoading.stopAnimator();
        myLoading.setVisibility(GONE);
    }

    /*刷新结束时状态*/
    public void setRefreshState(int flag) {
        if (flag == 0) tvHeader.setText(REFRESH_FAILURE);
        else if (flag == 1) {
            ivHeaderState.setVisibility(VISIBLE);
            tvHeader.setText(REFRESH_SUCCESS);
        }
        stopLoading(ivHeaderLoading);

        postDelayed(new Runnable() {
            @Override
            public void run() {
                headerAnimator.start();
            }
        }, HEADER_FOOTER_ANIMATOR_TIME);

    }

    /*加载结束时状态*/
    public void setLoadState(int flag) {
        if (flag == 0) tvFooter.setText(LOAD_FAILURE);
        else if (flag == 1) {
            ivFooterState.setVisibility(VISIBLE);
            tvFooter.setText(LOAD_SUCCESS);
        } else if (flag == 2) tvFooter.setText(LOAD_NOTHING);
        stopLoading(ivFooterLoading);

        postDelayed(new Runnable() {
            @Override
            public void run() {
                stopAll();
            }
        }, HEADER_FOOTER_ANIMATOR_TIME);
    }

    /*设置scrollView是否需要上拉提示语*/
    public MyRefresh setNoLoad(boolean isNoLoad) {
        this.isNoLoad = isNoLoad;
        return this;
    }

    public void setOnListerRefresh(IListerRefresh iListerRefresh) {
        this.iListerRefresh = iListerRefresh;
    }

    public interface IListerRefresh {
        void Refresh();

        void Load();
    }

}
