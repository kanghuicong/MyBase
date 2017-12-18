package com.kang.mybase;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.ArrayMap;
import android.view.View;

import com.google.gson.Gson;
import com.kang.mybase.activity.HeaderChooseActivity;
import com.kang.mybase.activity.PhotoChooseActivity;
import com.kang.mybase.base.BaseFragment;
import com.kang.mybase.data.FunData;
import com.kang.mybase.model.TestBean;
import com.kang.mybase.pro.IJsonData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.OnClick;

import static com.kang.utilssdk.ToastUtils.showShort;

/**
 * Created by KangHuiCong on 2017/12/11.
 * E-Mail is 515849594@qq.com
 */
public class Fragment1 extends BaseFragment  {

    FunData funData;
    List<TestBean> list = new ArrayList<>();
    @Override
    public int setLayout() {
        return R.layout.fragment1;
    }

    @Override
    public void init() {
        //do something......
        funData = new FunData<List<TestBean>>(this, this);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick({R.id.http_click,R.id.photo_click,R.id.header_click})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.http_click://网络请求
                Map<String, Object> map = new ArrayMap<>();
                funData.getData(map);
                break;
            case R.id.photo_click://图片选择
                startActivity(new Intent(getActivity(),PhotoChooseActivity.class));
                break;
            case R.id.header_click://头像
                startActivity(new Intent(getActivity(),HeaderChooseActivity.class));
                break;
        }
    }

    @Override
    public void success(Object t) {

//        TestBean person = gson.fromJson(t.toString(), TestBean.class);

        funData.getJsonData(t, new IJsonData() {
            @Override
            public void getJsonData(Gson gson,String json) {
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
