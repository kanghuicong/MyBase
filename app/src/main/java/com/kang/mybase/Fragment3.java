package com.kang.mybase;

import android.widget.ListView;

import com.kang.mybase.adapter.RefreshAdapter;
import com.kang.mybase.base.BaseFragment;
import com.kang.mybase.custom.view.MyRefresh;
import com.kang.mybase.fun.RefreshUtil;
import com.kang.mybase.model.RefreshAllBean;
import com.kang.mybase.pro.IRefresh;

import java.util.HashMap;
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
        Map<String, Object> map = new HashMap<>();
        refreshUtil = new RefreshUtil(getApi().test(map), myRefresh,listView, this,this);
    }

    @Override
    public Observable loadObservable() {
        Map<String, Object> map = new HashMap<>();
        return getApi().test(map);
    }

    @Override
    public void refreshSuccess(Object baseModelList) {
        refreshUtil.refreshSuccess(new RefreshAdapter(activity),((RefreshAllBean) baseModelList).getData());
    }

    @Override
    public void loadSuccess(Object baseModelList) {
        refreshUtil.loadSuccess(((RefreshAllBean) baseModelList).getData());
    }




    @Override
    public void success(Object baseModelList, String type) {}

    @Override
    public void failure(String error_code, String error_msg) {}
}
