package com.kang.mybase.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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

public abstract class BaseFragment extends Fragment implements ISubDelete{

    protected Activity activity;
    private View view;

    public Subscription baseSub;
    protected Gson gson ;
    protected abstract int setLayout();
    protected abstract void init();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = getActivity();
        gson = new Gson();
        if (view == null) {
            view = View.inflate(activity, setLayout(), null);
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
    public void deleteSub(Subscription baseSub) {
        this.baseSub = baseSub;
    }

}
