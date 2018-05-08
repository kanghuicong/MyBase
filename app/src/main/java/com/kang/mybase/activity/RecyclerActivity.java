package com.kang.mybase.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kang.mybase.R;
import com.kang.mybase.adapter.RecyclerAdapter;
import com.kang.mybase.base.BaseActivity;
import com.kang.mybase.custom.other.GvItemDecorationDivider;
import com.kang.mybase.util.inject.InjectActivityView;
import com.kang.utilssdk.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kanghuicong on 2018/5/7.
 * E-Mail:515849594@qq.com
 */
@InjectActivityView(R.layout.recycler)
@Route(path = "/activity/RecyclerActivity")
public class RecyclerActivity extends BaseActivity {

    @InjectView(R.id.rv)
    RecyclerView rv;

    @Override
    public void init() {

//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2, OrientationHelper.VERTICAL, false);
//        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);

        rv.setLayoutManager(mLayoutManager);

        rv.setHasFixedSize(true);
        List<String> list = new ArrayList();
        for (int i = 0; i < 20; i++) {
            list.add(i + "");
        }

        RecyclerAdapter mAdapter = new RecyclerAdapter(getApplicationContext(), list);
        rv.setAdapter(mAdapter);
        // 设置Item添加和移除的动画
        rv.setItemAnimator(new DefaultItemAnimator());
        // 设置Item之间间隔样式
//        rv.addItemDecoration(new LvItemDecorationDivider(this,  LinearLayoutManager.VERTICAL));
        rv.addItemDecoration(new GvItemDecorationDivider(this));

        mAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.showShort("position:" + position);
            }
        });
    }

    @Override
    public void success(Object baseModelList, String type) {

    }

    @Override
    public void failure(String error_code, String error_msg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
