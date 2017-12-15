package com.kang.mybase.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kang.mybase.pro.IHttp;
import com.kang.mybase.pro.ISubDelete;

import butterknife.ButterKnife;
import rx.Subscription;

import static com.kang.mybase.util.inject.InjectUtils.injectActivityView;

/**
 * Created by KangHuiCong on 2017/12/11.
 * E-Mail is 515849594@qq.com
 */

public abstract class BaseActivity extends BaseFragmentActivity implements ISubDelete , IHttp{

    public abstract void init();

    public Subscription baseSub;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectActivityView(this);//自定义注解
        ButterKnife.inject(this);
        init();
    }

    @Override
    public void deleteSub(Subscription baseSub) {
        this.baseSub = baseSub;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (baseSub!=null && !baseSub.isUnsubscribed())baseSub.unsubscribe();
    }
}
