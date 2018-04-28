package com.kang.mybase.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import com.kang.mybase.BR;

/**
 * Created by kanghuicong on 2018/4/24.
 * E-Mail:515849594@qq.com
 */

public class DataBindingBean {
    public ObservableField<String> name=  new ObservableField<String>();
    public ObservableInt one=  new ObservableInt();
    public ObservableInt two=  new ObservableInt();

    public DataBindingBean() {
        one.set(1);
        two.set(2);
    }

    public void btClick(View view) {
        String num = one.get()+two.get()+"";
        name.set(num);
    }
}
