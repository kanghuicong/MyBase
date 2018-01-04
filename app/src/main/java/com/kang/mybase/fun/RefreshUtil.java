package com.kang.mybase.fun;

import com.kang.mybase.custom.MyRefresh;
import com.kang.mybase.pro.IRefresh;

import rx.Observable;

/**
 * Created by KangHuiCong on 2018/1/3.
 * E-Mail is 515849594@qq.com
 */

public class RefreshUtil implements MyRefresh.IListerRefresh{
    MyRefresh myRefresh;
    Observable observable;
    RefreshData refreshData;
    IRefresh iRefresh;

    public RefreshUtil(RefreshData refreshData, Observable observable,MyRefresh myRefresh,IRefresh iRefresh) {
        this.refreshData = refreshData;
        this.observable = observable;
        this.myRefresh = myRefresh;
        this.iRefresh = iRefresh;
        init();
    }

    public void init() {
        myRefresh.setOnListerRefresh(this);
    }

    @Override
    public void Refresh() {
        refreshData.getRefreshData(observable,iRefresh);
    }

    @Override
    public void Load() {
        refreshData.getLoadData(iRefresh.loadObservable(),iRefresh);
    }

}
