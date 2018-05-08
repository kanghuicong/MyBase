package com.kang.mybase.fragment;

import android.app.Activity;
import android.app.KeyguardManager;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kang.mybase.R;
import com.kang.mybase.base.BaseFragment;
import com.kang.mybase.bean.DataBindingBean;
import com.kang.mybase.bean.FileBean;
import com.kang.mybase.databinding.Fragment4Binding;
import com.kang.mybase.pro.ISubDelete;
import com.kang.utilssdk.LogUtils;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import java.net.DatagramSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import rx.Subscription;


/**
 * Created by KangHuiCong on 2017/12/11.
 * E-Mail is 515849594@qq.com
 */

public class Fragment4 extends Fragment implements ISubDelete {

    Fragment4Binding binding;
    public Subscription baseSub;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment4, container, false);
            initView();
        }
        return binding.getRoot();

    }

    DataBindingBean bean;

    private void initView() {
        binding.text.setText("");

        bean = new DataBindingBean();
        bean.name.set("hhh");
        binding.setDataBindingBean(bean);


    }

    @Override
    public void deleteSub(Subscription baseSub) {
        this.baseSub = baseSub;
    }
}
