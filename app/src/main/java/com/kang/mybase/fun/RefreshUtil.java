package com.kang.mybase.fun;

import android.widget.ListView;

import com.kang.mybase.adapter.MyAdapter;
import com.kang.mybase.base.BaseItem;
import com.kang.mybase.custom.MyRefresh;
import com.kang.mybase.pro.IRefresh;
import com.kang.mybase.pro.ISubDelete;

import java.util.List;

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
    MyAdapter myAdapter = null;
    ListView listView;
    ISubDelete iSubDelete;
    public RefreshUtil(Observable observable,MyRefresh myRefresh,ListView listView,IRefresh iRefresh,ISubDelete iSubDelete) {
        this.observable = observable;
        this.myRefresh = myRefresh;
        this.listView = listView;
        this.iRefresh = iRefresh;
        this.iSubDelete = iSubDelete;
        init();
    }

    public void init() {
        refreshData = new RefreshData(myRefresh, iSubDelete);
        //第一次进入自动刷新数据
        refreshData.getRefreshData(observable,iRefresh);
        //上拉下拉监听
        myRefresh.setOnListerRefresh(this);
    }

    @Override//刷新数据
    public void Refresh() {
        refreshData.getRefreshData(observable,iRefresh);
    }

    @Override//加载数据
    public void Load() {
        refreshData.getLoadData(iRefresh.loadObservable(),iRefresh);
    }

    //刷新数据成功，配置adapter
    public void refreshSuccess(BaseItem mItem,List list) {
        if (myAdapter == null) {
            myAdapter = new MyAdapter(mItem);
            myAdapter.reRefreshData(list);
            listView.setAdapter(myAdapter);
        } else {
            myAdapter.reRefreshData(list);
        }
    }

    //加载数据成功，更新adapter
    public void loadSuccess(List list) {
        myAdapter.reLoadData(list);
    }

}
