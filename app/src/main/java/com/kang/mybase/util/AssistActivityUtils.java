package com.kang.mybase.util;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import static com.kang.mybase.util.BarUtils.getStatusBarHeight;

/**
 * Created by KangHuiCong on 2017/12/8.
 * E-Mail is 515849594@qq.com
 */

public final class AssistActivityUtils {
    //当使用沉浸式状态栏会有些问题，特别是EditText在底部键盘弹起时，沉浸式失效，所以需要重新计算布局高度
    //在computeUsableHeight方法中，我加了一个getStatusBarHeight()高度，因为部分机型含有底部虚拟按键
    //不加的话界面底部会多出一个状态栏的高度，加不加对普通机型都没有影响
    public static void assistActivity(View viewObserving) {
        new AssistActivityUtils(viewObserving);
    }

    private View mViewObserved;//被监听的视图
    private int usableHeightPrevious;//视图变化前的可用高度
    private ViewGroup.LayoutParams frameLayoutParams;

    private AssistActivityUtils(View viewObserving) {
        mViewObserved = viewObserving;
        //给View添加全局的布局监听器
        mViewObserved.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                resetLayoutByUsableHeight(computeUsableHeight());
            }
        });
        frameLayoutParams = mViewObserved.getLayoutParams();
    }

    private void resetLayoutByUsableHeight(int usableHeightNow) {
        //比较布局变化前后的View的可用高度
        if (usableHeightNow != usableHeightPrevious) {
            //如果两次高度不一致
            //将当前的View的可用高度设置成View的实际高度
            frameLayoutParams.height = usableHeightNow;
            mViewObserved.requestLayout();//请求重新布局
            usableHeightPrevious = usableHeightNow;
        }
    }

    /**
     * 计算视图可视高度
     *
     * @return
     */
    private int computeUsableHeight() {
        Rect r = new Rect();
        mViewObserved.getWindowVisibleDisplayFrame(r);
        return (r.bottom - r.top)+getStatusBarHeight();//当含有虚拟键时
    }
}
