package com.kang.mybase.fun;

import android.content.Context;

import com.kang.mybase.base.BaseFragmentActivity;
import com.kang.mybase.custom.MyRefresh;
import com.kang.mybase.model.TestBean;

import java.util.List;

import rx.Observable;

/**
 * Created by KangHuiCong on 2018/1/3.
 * E-Mail is 515849594@qq.com
 */

public class RefreshUtil implements MyRefresh.IListerRefresh {
    MyRefresh myRefresh;
    Observable observable;
    RefreshData refreshData;

    public RefreshUtil(RefreshData refreshData, Observable observable,MyRefresh myRefresh) {
        this.refreshData = refreshData;
        this.observable = observable;
        this.myRefresh = myRefresh;

        init();
    }

    public void init() {
        myRefresh.setOnListerRefresh(this);
    }

    @Override
    public void Refresh() {
        refreshData.getRefreshData(observable);
    }

    @Override
    public void Load() {
        refreshData.getLoadData(observable);
    }
}
