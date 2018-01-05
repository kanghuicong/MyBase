package com.kang.mybase.activity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;

import com.kang.mybase.R;
import com.kang.mybase.custom.view.MyDialog;
import com.kang.mybase.pro.IDialog;
import com.kang.utilssdk.PermissionUtils;
import com.kang.utilssdk.PermissionUtils.OnPermissionListener;
import com.kang.utilssdk.SharedPreferencesUtils;

import static com.kang.utilssdk.AppUtils.getAppDetailsSettings;
import static com.kang.utilssdk.PermissionUtils.getDeniedPermissions;
import static com.kang.utilssdk.PermissionUtils.hasAlwaysDeniedPermission;
import static com.kang.utilssdk.SharedPreferencesUtils.putPreferences;
import static com.kang.utilssdk.ToastUtils.showShort;


/**
 * Created by KangHuiCong on 2017/12/11.
 * E-Mail is 515849594@qq.com
 */

public class SplashActivity extends Activity implements OnPermissionListener {

    private OnPermissionListener mOnPermissionListener;
    private boolean flag = false;
    private DialogInterface dialog;
    private String IS_FIRST = "is_first";

    String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View view = View.inflate(SplashActivity.this, R.layout.main_splash, null);

        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(2000);

        view.setAnimation(animation);
        setContentView(view);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        flag = true;
        if (requestCode != -1 && requestCode == requestCode) {
            if (mOnPermissionListener != null) {
                String[] deniedPermissions = getDeniedPermissions(this, permissions);
                if (deniedPermissions.length > 0) {
                    mOnPermissionListener.onPermissionDenied(deniedPermissions);
                } else {
                    mOnPermissionListener.onPermissionGranted();
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!flag) {
            mOnPermissionListener = this;
            //权限申请，当执行onRequestPermissionsResult，activity会走onResume，进入死循环，用flag标记
            //写在onResume方法里主要是考虑进入权限设置后却依然没有给予权限，返回界面继续弹框
            //方法内会系统版本，小于23的只需要在清单设置可
            PermissionUtils.requestPermissions(SplashActivity.this, 10001, permissions, this);
        }
    }

    @Override
    public void onPermissionGranted() {
        showShort("授权成功");
        if (dialog != null) dialog.dismiss();
        new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (!SharedPreferencesUtils.getPreferences(SplashActivity.this, IS_FIRST, false)) {
                    //第一次进入APP，可以在此设置进入引导页
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                } else {
                    //正常进入APP
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
                putPreferences(SplashActivity.this, IS_FIRST, true);
                return true;
            }
        }).sendEmptyMessageDelayed(0, 2000);
    }

    @Override
    public void onPermissionDenied(String[] deniedPermissions) {
        if (hasAlwaysDeniedPermission(SplashActivity.this, permissions)) {
            new MyDialog("设置", "是否前往设置权限", "取消", "确定", new IDialog() {
                @Override
                public void leftClick(DialogInterface dialog) {
                    dialog.dismiss();
                    finish();
                    System.exit(0);
                }
                @Override
                public void rightClick(DialogInterface dialog) {
                    SplashActivity.this.dialog = dialog;
                    flag = false;
                    //前往设置
                    getAppDetailsSettings();
                }
            }).isFinish(true).show(getFragmentManager(), "settingDialog");
        } else {
            showShort("授权失败");
            finish();
            System.exit(0);
        }
    }

}
