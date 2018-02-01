package com.kang.mybase.activity;

import android.os.Bundle;

import com.kang.mybase.MyApplication;
import com.kang.mybase.R;
import com.kang.mybase.base.BaseActivity;
import com.kang.mybase.util.inject.InjectActivityView;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kang.utilssdk.ToastUtils.showShort;

/**
 * Created by kanghuicong on 2018/2/1.
 * E-Mail:515849594
 */
@InjectActivityView(R.layout.login)
public class LoginActivity extends BaseActivity {

    @Override
    public void success(Object baseModelList, String type) {

    }

    @Override
    public void failure(String error_code, String error_msg) {

    }

    @Override
    public void init() {

    }


    @OnClick(R.id.bt_login)
    public void onViewClicked() {
        MyApplication.UserId = "123";
        showShort("登录成功");
        finish();
    }
}
