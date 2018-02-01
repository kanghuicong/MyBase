package com.kang.mybase.fun;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

import static com.kang.mybase.MyApplication.UserId;
import static com.kang.utilssdk.ToastUtils.showShort;

/**
 * Created by kanghuicong on 2018/2/1.
 * E-Mail:515849594
 */
@Interceptor(priority = 1, name = "拦截未登录")
public class LoginInterceptor implements IInterceptor {

    public static final int CHECK_LOGIN = 1;
    public static final String NO_LOGIN = "账号未登录";

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        if (postcard.getExtra()== CHECK_LOGIN && UserId.equals("")) {
            showShort("账号未登录");
            callback.onInterrupt(new RuntimeException(NO_LOGIN));
        } else callback.onContinue(postcard);
    }

    @Override
    public void init(Context context) {

    }
}