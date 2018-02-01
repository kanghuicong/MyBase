package com.kang.mybase.fragment;

import android.widget.ListView;

import com.kang.mybase.R;
import com.kang.mybase.adapter.RefreshAdapter;
import com.kang.mybase.adapter.RefreshAdapter2;
import com.kang.mybase.base.BaseFragment;
import com.kang.mybase.custom.view.MyListView;
import com.kang.mybase.custom.view.MyRefresh;
import com.kang.mybase.fun.RefreshUtil;
import com.kang.mybase.model.RefreshAllBean;
import com.kang.mybase.model.RefreshItemBean;
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
    MyRefresh myRefresh;

    RefreshUtil refreshUtil;

    @Override
    public int setLayout() {
        return R.layout.fragment3;
    }

    @Override
    public void init() {
        //刷新数据
        refreshUtil = new RefreshUtil(myRefresh,listView, this,this);
    }

    @Override
    public Observable refreshObservable() {
        Map<String, Object> map = new HashMap<>();
        return getApi().test(map);
    }

    @Override
    public Observable loadObservable() {
        Map<String, Object> map = new HashMap<>();
        return getApi().test(map);
    }

    @Override
    public void refreshSuccess(Object baseModelList) {
        List<RefreshItemBean> list = new ArrayList<>();
        for (int i=0;i<15;i++) {
            RefreshItemBean refreshItemBean = new RefreshItemBean();
            refreshItemBean.setName("音乐类"+i);
            list.add(refreshItemBean);
        }
//        refreshUtil.refreshSuccess(new RefreshAdapter(activity),((RefreshAllBean) baseModelList).getData());
        refreshUtil.refreshSuccess(new RefreshAdapter(activity),list);

//        RefreshAdapter2 refreshAdapter2 = new RefreshAdapter2(activity);
//        refreshAdapter2.refreshData(list);
//        listView.setAdapter(refreshAdapter2);
    }

    @Override
    public void loadSuccess(Object baseModelList) {
//        refreshUtil.loadSuccess(((RefreshAllBean) baseModelList).getData());
        List<RefreshItemBean> list = new ArrayList<>();
        for (int i=0;i<5;i++) {
            RefreshItemBean refreshItemBean = new RefreshItemBean();
            refreshItemBean.setName("音乐类"+i);
            list.add(refreshItemBean);
        }
        refreshUtil.loadSuccess(list);
    }

    @Override
    public void success(Object baseModelList, String type) {}

    @Override
    public void failure(String error_code, String error_msg) {}
}
