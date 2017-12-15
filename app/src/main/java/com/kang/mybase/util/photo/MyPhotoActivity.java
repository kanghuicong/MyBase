package com.kang.mybase.util.photo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.kang.mybase.R;
import com.litao.android.lib.Configuration;
import com.litao.android.lib.GalleryActivity;
import com.litao.android.lib.entity.PhotoEntry;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.kang.mybase.util.BarUtils.getStatusBarHeight;
import static com.kang.mybase.util.SizeUtils.dp2px;

/**
 * Created by KangHuiCong on 2017/12/15.
 * E-Mail is 515849594@qq.com
 */

public class MyPhotoActivity extends GalleryActivity {

    List<PhotoEntry> mSelectedPhotos;
    @InjectView(R.id.selected_count)
    TextView selectedCount;
    @InjectView(R.id.gallery_root)
    FrameLayout galleryRoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_photo);
        ButterKnife.inject(this);
        EventBus.getDefault().register(this);

        galleryRoot.setPadding(0,dp2px(40)+getStatusBarHeight(),0,dp2px(40));
        attachFragment(R.id.gallery_root);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public Configuration getConfiguration() {
        //返回控件配置
        Configuration cfg = new Configuration.Builder()
                .hasCamera(true)
                .hasShade(true)
                .hasPreview(true)
                .setSpaceSize(3)
                .setPhotoMaxWidth(120)
                .setCheckBoxColor(0xFF3F51B5)
                .setDialogHeight(Configuration.DIALOG_HALF)
                .setDialogMode(Configuration.DIALOG_GRID)
                .setMaximum(3)
                .setTip(null)
                .setAblumsTitle(null)
                .build();
        return cfg;
    }

    @Override
    public List<PhotoEntry> getSelectPhotos() {
        //返回当前已经选中的图片 没有是返回null
        return mSelectedPhotos;
    }


    @Override
    public void onSelectedCountChanged(int count) {
        //这个方法将在你图选择发生变化时调用
        // count:当前被选中图像数量
        selectedCount.setVisibility(count > 0 ? View.VISIBLE : View.INVISIBLE);
        selectedCount.setText(String.valueOf(count));
    }

    @Override
    public void onAlbumChanged(String name) {
        //这个方法将在相册选择发生变化时调用
        //name:当前选中的相册名称

    }

    @Override
    public void onTakePhoto(PhotoEntry entry) {
        //这个方法将在你拍照后调用
        //entry：返回拍照后的图片信息
        EventBus.getDefault().post(entry);
        finish();
    }

    @Override
    public void onChoosePhotos(List<PhotoEntry> entries) {
        //这个方法将在你调用 sendPhotos() 方法后调用
        //entries：返回你选中的图片信息
        EventBus.getDefault().post(new PhotoBean(entries, PhotoBean.RECEIVED_PHOTOS_ID));
        finish();
    }

    @Override
    public void onPhotoClick(PhotoEntry entry) {
        //这个方法将在你点击图像时调用，如果configuration hasPreview(false)时 将不回调此方法
        //entry: 返回当前点击的图像信息

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void photosMessageEvent(PhotoBean entry) {
        if (entry.id == PhotoBean.SELECTED_PHOTOS_ID) {
            mSelectedPhotos = entry.photos;
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @OnClick({R.id.album, R.id.selected_count, R.id.send_photos})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.album:
                openAlbum();
                break;
            case R.id.send_photos:
                sendPhotos();
                break;
        }
    }
}
