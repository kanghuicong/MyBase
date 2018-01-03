package com.kang.mybase;

import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.kang.mybase.adapter.DisAdapter;
import com.kang.mybase.base.BaseFragment;
import com.kang.mybase.custom.MyRefresh;
import com.kang.mybase.fun.FunData;
import com.kang.mybase.fun.RefreshData;
import com.kang.mybase.fun.RefreshUtil;
import com.kang.mybase.model.TestBean;
import com.kang.mybase.model.TestBean2;
import com.kang.mybase.pro.IRefresh;
import com.kang.utilssdk.AssistActivityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.kang.mybase.util.httpClient.HttpRequest.getApi;

/**
 * Created by KangHuiCong on 2017/12/11.
 * E-Mail is 515849594@qq.com
 */

public class Fragment3 extends BaseFragment implements IRefresh{


    @InjectView(R.id.listView)
    ListView listView;
    @InjectView(R.id.refresh)
    MyRefresh refresh;
    RefreshData refreshData;

    List<String> mList = new ArrayList<>();
    DisAdapter disAdapter = null;
    @Override
    public int setLayout() {
        return R.layout.fragment3;
    }

    @Override
    public void init() {
        //do something...
        refreshData = new RefreshData((MainActivity)activity,refresh,this, this);
        Map<String, Object> map = new HashMap<>();
        new RefreshUtil(refreshData, getApi().test(map), refresh);

        refreshData.getRefreshData(getApi().test(map));

    }

    @Override
    public void success(Object baseModelList, String type) {}

    @Override
    public void failure(String error_code, String error_msg) {}

    @Override
    public void refreshSuccess(Object baseModelList) {
        List<String> list2 = new ArrayList<>();
        for (int i=0;i<10;i++) {
            list2.add(i + "");
        }

        if (mList==null || mList.isEmpty()) {
            mList.addAll(list2);
            disAdapter = new DisAdapter(getActivity(), mList);
            listView.setAdapter(disAdapter);
        } else {
            mList.clear();
            mList.addAll(list2);
            disAdapter.changeCount(mList.size());
            disAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void refreshFailure(String error_code, String error_msg) {}

    @Override
    public void loadSuccess(Object baseModelList) {
        List<String> mList2 = new ArrayList<>();
        for (int i=10;i<20;i++) {
            mList2.add(i+"");
        }
        mList.addAll(mList2);
        disAdapter.changeCount(mList.size());
        disAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadFailure(String error_code, String error_msg) {}
}
