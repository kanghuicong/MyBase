package com.kang.mybase;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kang.mybase.base.BaseFragment;
import com.kang.mybase.custom.MyDialog;
import com.kang.mybase.custom.MyLoading;
import com.kang.mybase.pro.IDialog;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.kang.utilssdk.AppUtils.getAppDetailsSettings;

/**
 * Created by KangHuiCong on 2017/12/11.
 * E-Mail is 515849594@qq.com
 */

public class Fragment4 extends BaseFragment {

    @Override
    public int setLayout() {
        return R.layout.fragment4;
    }

    @Override
    public void init() {

    }

    @Override
    public void success(Object baseModelList, String type) {

    }

    @Override
    public void failure(String error_code, String error_msg) {

    }

}
