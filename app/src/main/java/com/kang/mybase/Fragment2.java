package com.kang.mybase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kang.mybase.base.BaseFragment;
import com.kang.mybase.custom.MySuperItem;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by KangHuiCong on 2017/12/11.
 * E-Mail is 515849594@qq.com
 */
public class Fragment2 extends BaseFragment {
    @InjectView(R.id.item)
    MySuperItem item;

    @Override
    public int setLayout() {
        return R.layout.fragment2;
    }

    @Override
    public void init() {
        item.setLeftText("555")
                .setLeftImageL("http://p0.so.qhmsg.com/t014156c14c469bae95.jpg")
                .setRightText("666")
                .setRightTextColor(R.color.main_color)
                .setTopLeftText("123");


    }


    @Override
    public void success(Object baseModelList) {

    }

    @Override
    public void failure(String error_code, String error_msg) {

    }

}
