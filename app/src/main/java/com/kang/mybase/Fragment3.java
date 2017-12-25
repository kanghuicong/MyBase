package com.kang.mybase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.kang.mybase.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by KangHuiCong on 2017/12/11.
 * E-Mail is 515849594@qq.com
 */

public class Fragment3 extends BaseFragment {


    @InjectView(R.id.listView)
    ListView listView;

    List<Map<String,String>> list = new ArrayList<>();
    @Override
    public int setLayout() {
        return R.layout.fragment3;
    }

    @Override
    public void init() {
        //do something...
        for (int i=0;i<100;i++) {
            Map map = new HashMap();
            map.put("content", i + "");
            list.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), list, R.layout.item_voice, new String[]{"content"}, new int[]{R.id.name});
        listView.setAdapter(simpleAdapter);

    }

    @Override
    public void success(Object baseModelList, String type) {

    }

    @Override
    public void failure(String error_code, String error_msg) {

    }
}
