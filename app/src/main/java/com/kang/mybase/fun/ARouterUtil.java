package com.kang.mybase.fun;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.TextUtils;
import com.kang.mybase.activity.LoginActivity;
import com.kang.mybase.custom.view.MyDialog;
import com.kang.mybase.pro.IDialog;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.kang.mybase.fun.LoginInterceptor.NO_LOGIN;
import static com.kang.utilssdk.ToastUtils.showShort;

/**
 * Created by kanghuicong on 2018/2/1.
 * E-Mail:515849594
 */

public class ARouterUtil {

    public static NavigationCallback getLogin(final Activity activity) {
        return new NavigationCallback() {
            @Override
            public void onFound(Postcard postcard) {}
            @Override
            public void onLost(Postcard postcard) {}
            @Override
            public void onArrival(Postcard postcard) {}
            @Override
            public void onInterrupt(Postcard postcard) {
                if (postcard.getTag().equals(NO_LOGIN)) {
                    activity.startActivity(new Intent(activity,LoginActivity.class));
                }
            }
        };
    }

    //主页
    public static void startMainActivity() {
        ARouter.getInstance().build("/activity/MainActivity").navigation();
    }
    //图片选择
    public static void startPhotoChooseActivity(Activity activity) {
        ARouter.getInstance().build("/activity/PhotoChooseActivity").navigation(activity,getLogin(activity));
    }
    //头像
    public static void startHeaderChooseActivity(Activity activity){
        ARouter.getInstance().build("/activity/HeaderChooseActivity").navigation(activity,getLogin(activity));
    }
    //音频
    public static void startVoiceActivity(){
        ARouter.getInstance().build("/activity/VoiceActivity").navigation();
    }
    //视频
    public static void startVideoActivity(){
        ARouter.getInstance().build("/activity/VideoActivity").navigation();
    }

    //RecyclerActivity
    public static void startRecyclerActivity(){
        ARouter.getInstance().build("/activity/RecyclerActivity").navigation();
    }


}
