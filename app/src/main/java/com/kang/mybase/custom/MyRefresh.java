package com.kang.mybase.custom;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
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
    int pageCount = 10;
    boolean isNoLoad = false;

    private final static int STOP = 0;
    private final static int ING = 1;
    private final static int IS_FOOTER = 2;
    private final static int IS_HEADER = 3;
//    private final static int IS_NOTHING = 4;

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

    Handler handler = new Handler();
    IListerRefresh iListerRefresh;

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

        measureView(headerView);
        mHeaderHeight = headerView.getMeasuredHeight();
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, mHeaderHeight);
        params.topMargin = -(mHeaderHeight);
        addView(headerView, params);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        /*加载完布局后，添加footer*/
        footerView = View.inflate(context, R.layout.refresh_footer, null);
        tvFooter = (TextView) footerView.findViewById(R.id.tv_footer);
        ivFooterState = (ImageView) footerView.findViewById(R.id.iv_state);

        measureView(footerView);
        mFooterHeight = footerView.getMeasuredHeight();
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, mFooterHeight);
        addView(footerView, params);

        /*获取AbsListView，目前设计的布局限制为header,AbsListView/ScrollView,footer*/
        if (getChildAt(1) instanceof AbsListView) {
            listerView = (AdapterView<?>) getChildAt(1);
        }else if (getChildAt(1) instanceof ScrollView) {
            scrollView = (ScrollView) getChildAt(1);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int y = (int) e.getRawY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                /*header-->大于0,footer-->小于0*/
                int deltaY = y - startY;
                if ((deltaY > 0 && mFooterState == ING) || (deltaY < 0 && mHeaderState == ING))
                    setHeaderTopMargin(-mHeaderHeight);
                if (isRefreshViewScroll(deltaY)) return true;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaY = y - startY;
                startY = y;
                /*已经到了header*/
                if (mPullState == IS_HEADER && mFooterState == STOP) {
                    int topMargin = changeHeaderViewTopMargin(deltaY);
                    /*header完全展示*/
                    if (topMargin > 0) {
                        tvHeader.setText(REFRESH_2);
                        mHeaderState = ING;
                    }
                } else if (mPullState == IS_FOOTER && mHeaderState == STOP) {
                    int topMargin = changeHeaderViewTopMargin(deltaY);
                    if (!isNoLoad && (Math.abs(topMargin) >= (mHeaderHeight + mFooterHeight))) {
                        tvFooter.setText(LOAD_2);
                        mFooterState = ING;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (mPullState == IS_HEADER ) {
                    if (mHeaderState == ING) {
                        tvHeader.setText(REFRESH_3);
                        setHeaderTopMargin(0);
                        doHandler(runnableRefresh);
                    } else stopAll();
                } else if (mPullState == IS_FOOTER) {
                    if (mFooterState == ING) {
                        tvFooter.setText(LOAD_3);
                        setHeaderTopMargin(-(mHeaderHeight + mFooterHeight));
                        doHandler(runnableLoad);
                    } else stopAll();
                } else if (isNoLoad) {
                    setHeaderTopMargin(-mHeaderHeight + mFooterHeight);
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    /*measure子View*/
    private void measureView(View child) {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null) p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;

        if (lpHeight > 0) childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
        else childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);

        child.measure(childWidthSpec, childHeightSpec);
    }

    /*设置header的topMargin*/
    private void setHeaderTopMargin(int topMargin) {
        LayoutParams params = (LayoutParams) headerView.getLayoutParams();
        params.topMargin = topMargin;
        headerView.setLayoutParams(params);
        invalidate();
    }

    /*设置header的topMargin及回弹系数0.3f*/
    private int changeHeaderViewTopMargin(int deltaY) {
        LayoutParams params = (LayoutParams) headerView.getLayoutParams();
        float newTopMargin = params.topMargin + deltaY * 0.3f;
        params.topMargin = (int) newTopMargin;
        headerView.setLayoutParams(params);
        invalidate();
        return params.topMargin;
    }

    private boolean isRefreshViewScroll(int deltaY) {
        if (listerView != null) {
            View child = listerView.getChildAt(0);
            /*没有数据*/
            /*item(ListView/GridView)滑动到最顶端*/
            if (deltaY > 0 && child.getTop() == 0) {
                tvHeader.setText(REFRESH_1);
                mPullState = IS_HEADER;
                return true;
            } else if (deltaY < 0) {
                if (child == null && listerView.getChildCount()<pageCount) return false;

                View lastChild = listerView.getChildAt(listerView.getChildCount() - 1);
                if (lastChild == null) return false;
                /*item(ListView/GridView)滑动到最底部*/
                if (lastChild.getBottom() <= getHeight()) {
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
            }else if (deltaY < 0){
                if (child == null) return false;

                View lastChild = scrollView.getChildAt(scrollView.getChildCount() - 1);
                if (lastChild == null) return false;
                if (lastChild.getBottom() <= getHeight()) {
                    if (!isNoLoad)tvFooter.setText(LOAD_1);
                    mPullState = IS_FOOTER;
                    return true;
                }
            }
        }
        return false;
    }

    private Runnable runnableRefresh = new Runnable() {
        public void run() {
            if (iListerRefresh!=null)iListerRefresh.Refresh();
            else setRefreshState(1);
        }
    };

    private Runnable runnableLoad = new Runnable() {
        public void run() {
            if (iListerRefresh!=null)iListerRefresh.Load();
            else setLoadState(1);
        }
    };

    private void doHandler(Runnable runnable) {
        /*ING时，重复下拉/上拉，移除之前的runnable*/
        if (isRepeat) {
            if (handler != null && runnable != null) {
                handler.removeCallbacks(runnable);
            }
        }
        isRepeat = true;
        handler.postDelayed(runnable, 500);
    }

    private void stopAll() {
        setHeaderTopMargin(-mHeaderHeight);
        if (ivHeaderState.getVisibility()==VISIBLE)ivHeaderState.setVisibility(GONE);
        if (ivFooterState.getVisibility()==VISIBLE)ivFooterState.setVisibility(GONE);
        tvHeader.setText(REFRESH_1);
        if (!isNoLoad)tvFooter.setText(LOAD_1);
        mHeaderState = STOP;
        mFooterState = STOP;
        isRepeat = false;
    }

    /*刷新结束时状态*/
    public void setRefreshState(int flag) {
        if(flag==0)tvHeader.setText(REFRESH_FAILURE);
        else if (flag==1){
            ivHeaderState.setVisibility(VISIBLE);
            tvHeader.setText(REFRESH_SUCCESS);
        }
        postDelayed(new Runnable() {
            @Override
            public void run() {
                stopAll();
            }
        }, 500);
    }

    /*加载结束时状态*/
    public void setLoadState(int flag) {
        if (flag==0)tvFooter.setText(LOAD_FAILURE);
        else if(flag==1) {
            ivFooterState.setVisibility(VISIBLE);
            tvFooter.setText(LOAD_SUCCESS);
        } else if (flag==2)tvFooter.setText(LOAD_NOTHING);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                stopAll();
            }
        }, 500);
    }

    /*设置list每页个数*/
    private MyRefresh setPageCount(int pageCount) {
        this.pageCount = pageCount;
        return this;
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
