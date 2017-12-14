package com.kang.mybase;

import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.kang.mybase.base.BaseFragment;
import com.kang.mybase.data.TestData;
import com.kang.mybase.model.TestBean;
import com.kang.mybase.pro.IGetData;
import com.kang.mybase.pro.IHttp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.OnClick;

import static com.kang.mybase.util.ToastUtils.showShort;

/**
 * Created by KangHuiCong on 2017/12/11.
 * E-Mail is 515849594@qq.com
 */
public class Fragment1 extends BaseFragment implements IHttp {

    TestData testData;
    List<TestBean> list = new ArrayList<>();
    @Override
    public int setLayout() {
        return R.layout.fragment1;
    }

    @Override
    public void init() {
        //do something......
        testData = new TestData<List<TestBean>>(this, this);
    }

    @OnClick(R.id.http_click)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.http_click:
                testData.getTestData();
                break;
        }
    }

    @Override
    public void success(Object t) {

//        TestBean person = gson.fromJson(t.toString(), TestBean.class);

        testData.getListData(t, new IGetData() {
            @Override
            public void getData(Gson gson,String json) {
                TestBean person = gson.fromJson(json, TestBean.class); // String转化成JavaBean
                list.add(person); // 加入List
            }
        });
        showShort("success:" + list.get(0).getName());
    }

    @Override
    public void failure(String error_code, String error_msg) {

    }


}
