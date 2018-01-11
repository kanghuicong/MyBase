package com.kang.mybase.fun;

import android.widget.ListView;

import com.kang.mybase.base.BaseMyAdapter;
import com.kang.mybase.model.BaseItem;
import com.kang.mybase.custom.view.MyRefresh;
import com.kang.mybase.pro.IRefresh;
import com.kang.mybase.pro.ISubDelete;

import java.util.List;

import rx.Observable;

/**
 * Created by KangHuiCong on 2018/1/3.
 * E-Mail is 515849594@qq.com
 */

public class RefreshUtil implements MyRefresh.IListerRefresh{
    private MyRefresh myRefresh;
    private RefreshData refreshData;
    private IRefresh iRefresh;
    private BaseMyAdapter myAdapter = null;
    private ListView listView;
    private ISubDelete iSubDelete;

    public RefreshUtil(MyRefresh myRefresh,ListView listView,IRefresh iRefresh,ISubDelete iSubDelete) {
        this.myRefresh = myRefresh;
        this.listView = listView;
        this.iRefresh = iRefresh;
        this.iSubDelete = iSubDelete;
        init();
    }

    public void init() {
        refreshData = new RefreshData(myRefresh, iSubDelete);
        //第一次进入自动刷新数据
        refreshData.getRefreshData(iRefresh.refreshObservable(),iRefresh);
        //上拉下拉监听
        myRefresh.setOnListerRefresh(this);
    }

    @Override//刷新数据
    public void Refresh() {
        refreshData.getRefreshData(iRefresh.refreshObservable(),iRefresh);
    }

    @Override//加载数据
    public void Load() {
        refreshData.getLoadData(iRefresh.loadObservable(),iRefresh);
    }

    //刷新数据成功，配置adapter
    public void refreshSuccess(BaseItem mItem,List list) {
        if (myAdapter == null) {
            myAdapter = new BaseMyAdapter(mItem);
            listView.setAdapter(myAdapter);
        }
        myAdapter.reRefreshData(list);
    }

    //加载数据成功，更新adapter
    public void loadSuccess(List list) {
        myAdapter.reLoadData(list);
    }

}
