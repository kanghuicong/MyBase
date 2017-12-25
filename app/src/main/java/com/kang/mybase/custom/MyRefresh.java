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
import android.widget.LinearLayout;

import com.kang.mybase.R;

/**
 * Created by KangHuiCong on 2017/12/25.
 * E-Mail is 515849594@qq.com
 */

public class MyRefresh extends LinearLayout {
    Context context;

    View headerView;
    AdapterView<?> listerView;
    View footerView;

    int mHeaderHeight;
    int mFooterHeight;

    int startY;

    private final static int STOP = 0;
    private final static int ING = 1;
    private final static int IS_FOOTER = 2;
    private final static int IS_HEADER = 3;

    int mHeaderState = STOP;
    int mFooterState = STOP;
    int mPullState;
    boolean isRepeat = false;


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
        measureView(footerView);
        mFooterHeight = footerView.getMeasuredHeight();
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, mFooterHeight);
        addView(footerView, params);

        if (getChildAt(1) instanceof AbsListView) {
            listerView = (AdapterView<?>) getChildAt(1);
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
                // header-->大于0,footer-->小于0
                int deltaY = y - startY;
                if ((deltaY > 0 && mFooterState == ING) || (deltaY < 0 && mHeaderState == ING)) {
                    setHeaderTopMargin(-mHeaderHeight);
                }
                if (isRefreshViewScroll(deltaY)) {
                    return true;
                }
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
                // onInterceptTouchEvent已经记录
                // mLastMotionY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaY = y - startY;
                startY = y;
                /*已经到了header*/
                if (mPullState == IS_HEADER && mFooterState == STOP) {
                    int topMargin = changeHeaderViewTopMargin(deltaY);
                    /*header完全展示*/
                    if (topMargin > 0) {
                        mHeaderState = ING;
                    }

                } else if (mPullState == IS_FOOTER && mHeaderState == STOP) {
                    int topMargin = changeHeaderViewTopMargin(deltaY);
                    if (Math.abs(topMargin) >= (mHeaderHeight + mFooterHeight)) {
                        mFooterState = ING;
                    }

                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (mPullState == IS_HEADER) {
                    if (mHeaderState == ING) {
                        setHeaderTopMargin(mHeaderHeight);
                        /*ING时，又再次下拉，移除之前的runnable*/
                        if (isRepeat) {
                            if (handler != null && runnable != null) {
                                handler.removeCallbacks(runnable);
                            }
                        }
                        isRepeat = true;
                        handler.postDelayed(runnable, 2000);
                    } else {
                        setHeaderTopMargin(-mHeaderHeight);
                    }
                } else if (mPullState == IS_FOOTER) {
                    if (mFooterState == ING) {
                        setHeaderTopMargin(-(mHeaderHeight + mFooterHeight));
                        if (isRepeat) {
                            if (handler != null && runnable != null) {
                                handler.removeCallbacks(runnable);
                            }
                        }
                        isRepeat = true;
                        handler.postDelayed(runnable, 2000);
                    } else {
                        setHeaderTopMargin(-mHeaderHeight);
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void measureView(View child) {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }

    private void setHeaderTopMargin(int topMargin) {
        LayoutParams params = (LayoutParams) headerView.getLayoutParams();
        params.topMargin = topMargin;
        headerView.setLayoutParams(params);
        invalidate();
    }

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
            if (child == null) {
                /*没有数据*/
                return false;
            }
            /*item(ListView/GridView)滑动到最顶端*/
            if (deltaY > 0 && child.getTop() == 0) {
                mPullState = IS_HEADER;
                return true;
            } else if (deltaY < 0) {
                View lastChild = listerView.getChildAt(listerView.getChildCount() - 1);
                if (lastChild == null) {
                    return false;
                }
                /*item(ListView/GridView)滑动到最底部*/
                if (lastChild.getBottom() <= getHeight()) {
                    mPullState = IS_FOOTER;
                    return true;
                }
            }
        }
        return false;
    }

    IListerRefresh iListerRefresh;

    public void setOnListerRefresh(IListerRefresh iListerRefresh) {
        this.iListerRefresh = iListerRefresh;
    }

    public interface IListerRefresh {
        void running();
    }

    Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run() {
            Log.i("khc----", "3333");
            setHeaderTopMargin(-mHeaderHeight);
            mHeaderState = STOP;
            mFooterState = STOP;
            isRepeat = false;
        }
    };

}
