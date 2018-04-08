package com.kang.mybase.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.kang.mybase.custom.view.MyDialog;
import com.kang.mybase.pro.INetChange;
import com.kang.utilssdk.NetworkUtils;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import static com.kang.utilssdk.KeyboardUtils.isShouldHideKeyboard;
import static com.kang.utilssdk.ToastUtils.showShort;


/**
 * Created by KangHuiCong on 2017/12/11.
 * E-Mail is 515849594@qq.com
 */

public abstract class BaseFragmentActivity extends FragmentActivity implements INetChange{

    public static INetChange iNetChange;
    private MyDialog myDialog;

    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        iNetChange = this;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        if (name.equals(LAYOUT_FRAMELAYOUT)) view = new AutoFrameLayout(context, attrs);

        if (name.equals(LAYOUT_LINEARLAYOUT)) view = new AutoLinearLayout(context, attrs);

        if (name.equals(LAYOUT_RELATIVELAYOUT)) view = new AutoRelativeLayout(context, attrs);

        if (view != null) return view;

        return super.onCreateView(name, context, attrs);
    }

    @Override //网络变化回调
    public void onNetChange(NetworkUtils.NetworkType netWorkType) {
        switch (netWorkType) {
            case NETWORK_WIFI:
                showShort("WIFI");
                break;
            case NETWORK_4G:
                showShort("4G");
                break;
            case NETWORK_3G:
                showShort("3G");
                break;
            case NETWORK_2G:
                showShort("2G");
                break;
            case NETWORK_UNKNOWN:
                showShort("网络无法识别");
                break;
            case NETWORK_NO:
                showShort("无网络");
                break;
        }
    }

    @Override //EditTex 点击空白位置隐藏软键盘
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v,ev)) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public void showLoading() {
        if (myDialog == null) myDialog = new MyDialog();
        myDialog.show(getFragmentManager(), "dialog");
    }

    public void dismissLoading() {
        if (myDialog!=null){
            myDialog.dismiss();
            myDialog.stopAnimator();
        }
    }
}

