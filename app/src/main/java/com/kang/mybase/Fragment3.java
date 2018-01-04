package com.kang.mybase;

import android.widget.ListView;

import com.kang.mybase.adapter.MyAdapter;
import com.kang.mybase.base.BaseFragment;
import com.kang.mybase.custom.MyRefresh;
import com.kang.mybase.fun.RefreshData;
import com.kang.mybase.fun.RefreshUtil;
import com.kang.mybase.model.TestBean;
import com.kang.mybase.model.TestBean2;
import com.kang.mybase.model.TestBean3;
import com.kang.mybase.pro.IRefresh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.InjectView;
import rx.Observable;

import static com.kang.mybase.util.httpClient.HttpRequest.getApi;

/**
 * Created by KangHuiCong on 2017/12/11.
 * E-Mail is 515849594@qq.com
 */

public class Fragment3 extends BaseFragment implements IRefresh {


    @InjectView(R.id.listView)
    ListView listView;
    @InjectView(R.id.refresh)
    MyRefresh refresh;
    RefreshData refreshData;

    MyAdapter myAdapter = null;
    int n = 1;

    @Override
    public int setLayout() {
        return R.layout.fragment3;
    }

    @Override
    public void init() {
        //do something...
        refreshData = new RefreshData(refresh, this);
        Map<String, Object> map = new HashMap<>();

        new RefreshUtil(refreshData, getApi().test(map), refresh,this);

        refreshData.getRefreshData(getApi().test(map),this);

    }

    @Override
    public void success(Object baseModelList, String type) {}

    @Override
    public void failure(String error_code, String error_msg) {}

    @Override
    public void refreshSuccess(Object baseModelList) {
        if (myAdapter==null) {
            myAdapter = new MyAdapter(new TestBean3(activity));
            myAdapter.reLoadData(((TestBean2) baseModelList).getData(),true);
            listView.setAdapter(myAdapter);
        } else {
            myAdapter.reLoadData(((TestBean2) baseModelList).getData(),true);
        }
    }

    @Override
    public void refreshFailure(String error_code, String error_msg) {}

    @Override
    public void loadSuccess(Object baseModelList) {
        myAdapter.reLoadData(((TestBean2) baseModelList).getData(),false);
    }

    @Override
    public void loadFailure(String error_code, String error_msg) {}

    @Override
    public Observable loadObservable() {
        Map<String, Object> map = new HashMap<>();
        return getApi().test(map);
    }

}
