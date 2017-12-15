package com.kang.mybase.util.photo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kang.mybase.R;
import com.kang.mybase.base.BaseActivity;
import com.litao.android.lib.Utils.GridSpacingItemDecoration;
import com.litao.android.lib.entity.PhotoEntry;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by KangHuiCong on 2017/12/15.
 * E-Mail is 515849594@qq.com
 */

public abstract class BasePhotoActivity extends BaseActivity implements ChooseAdapter.OnItmeClickListener{

    RecyclerView mRecyclerView;
    ChooseAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new ChooseAdapter(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(3, 4, true));
    }

    @Override
    public void onItemClicked(int position) {
        if (position == mAdapter.getItemCount() - 1) {
            startActivity(new Intent(this, MyPhotoActivity.class));
            EventBus.getDefault().postSticky(new PhotoBean(mAdapter.getData(), PhotoBean.SELECTED_PHOTOS_ID));
        } else {
            //点击大图
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void photosMessageEvent(PhotoBean entries) {
        if (entries.id == PhotoBean.RECEIVED_PHOTOS_ID) {
            mAdapter.reloadList(entries.photos);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void photoMessageEvent(PhotoEntry entry) {
        mAdapter.appendPhoto(entry);
    }

//    @Override
//    public void success(Object baseModelList) {
//
//    }
//
//    @Override
//    public void failure(String error_code, String error_msg) {
//
//    }
}
