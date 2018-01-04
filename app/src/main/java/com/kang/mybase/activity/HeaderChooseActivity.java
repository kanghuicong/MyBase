package com.kang.mybase.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.kang.mybase.R;
import com.kang.mybase.base.BaseActivity;
import com.kang.mybase.base.BaseHeaderActivity;
import com.kang.mybase.custom.CircleTransform;
import com.kang.mybase.custom.MyBottomDialog;
import com.kang.mybase.pro.IDialogBottom;
import com.kang.mybase.util.inject.InjectActivityView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.kang.headerpicker.PicUtils.CHOOSE_HEADER;
import static com.kang.headerpicker.PicUtils.PREVIEW_HEADER;
import static com.kang.headerpicker.PicUtils.TAKE_PICTURE_HEADER;
import static com.kang.utilssdk.ToastUtils.showShort;

/**
 * Created by KangHuiCong on 2017/12/18.
 * E-Mail is 515849594@qq.com
 */
@InjectActivityView(R.layout.header_activity)
public class HeaderChooseActivity extends BaseHeaderActivity implements IDialogBottom {
    List<String> list = new ArrayList<>();

    @InjectView(R.id.iv_header)
    ImageView ivHeader;

    @Override
    public void init() {
        list.add("图库");
        list.add("拍照");
    }

    @OnClick(R.id.iv_header)
    public void onViewClicked() {
        //底部弹起dialog,iDialog接口回调
        new MyBottomDialog(this, list, this);
    }

    @Override
    public void iDialog(int position) {
        //position即list的位置
        switch (position) {
            case 0://调用系统图库
                intoGallery();
                break;
            case 1://调用摄像头
                intoCamera();
                break;
        }
    }

    @Override
    public void getUrl(String url) {
        Glide.with(this).load(url).transform(new CircleTransform(this)).into(ivHeader);
    }

    @Override
    public void success(Object baseModelList,String type) {
    }

    @Override
    public void failure(String error_code, String error_msg) {
    }

}
