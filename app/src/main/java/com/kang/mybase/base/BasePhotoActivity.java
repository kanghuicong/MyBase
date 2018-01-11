package com.kang.mybase.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kang.mybase.R;
import com.kang.mybase.adapter.PhotoChooseAdapter;
import com.kang.mybase.activity.PhotoGalleryActivity;
import com.litao.android.lib.Utils.GridSpacingItemDecoration;
import com.litao.android.lib.entity.PhotoBean;
import com.litao.android.lib.entity.PhotoEntry;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by KangHuiCong on 2017/12/15.
 * E-Mail is 515849594@qq.com
 */

public abstract class BasePhotoActivity extends BaseActivity implements PhotoChooseAdapter.OnItemClickListener {

    RecyclerView mRecyclerView;
    PhotoChooseAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new PhotoChooseAdapter(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(3, 4, true));
    }

    @Override
    public void onItemClicked(int position) {
        if (position == mAdapter.getItemCount() - 1) {
            startActivity(new Intent(this, PhotoGalleryActivity.class));
            EventBus.getDefault().postSticky(new PhotoBean(mAdapter.getData(), PhotoBean.SELECTED_PHOTOS_ID));
        } else {
            //其他操作，如点击查看大图，点击删除之类的
            setItemClicked(position);
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

    public abstract void setItemClicked(int position);

    public int setMaximum() {
        return 6;
    }


}
