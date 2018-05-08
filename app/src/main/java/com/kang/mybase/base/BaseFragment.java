package com.kang.mybase.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.kang.mybase.pro.IHttp;
import com.kang.mybase.pro.ISubDelete;

import butterknife.ButterKnife;
import rx.Subscription;

/**
 * Created by KangHuiCong on 2017/12/11.
 * E-Mail is 515849594@qq.com
 */

public abstract class BaseFragment<T> extends Fragment implements ISubDelete , IHttp<T>{

    protected Activity activity;
    private View view;
    public Subscription baseSub;
    protected abstract int setLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);
    protected abstract void init();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = View.inflate(activity, setLayout(inflater,container,savedInstanceState), null);
            ButterKnife.inject(this, view);
            init();
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity)context;
    }

    @Override
    public void deleteSub(Subscription baseSub) {
        this.baseSub = baseSub;
    }

}
