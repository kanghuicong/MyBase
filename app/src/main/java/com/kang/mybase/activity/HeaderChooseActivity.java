package com.kang.mybase.activity;

import android.content.Intent;
import android.provider.MediaStore;
import com.kang.headerpicker.CircleImageView;
import com.kang.headerpicker.PicUtils;
import com.kang.mybase.R;
import com.kang.mybase.base.BaseActivity;
import com.kang.mybase.custom.MyBottomDialog;
import com.kang.mybase.pro.IDialogBottom;
import com.kang.mybase.util.inject.InjectActivityView;
import java.util.ArrayList;
import java.util.List;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.kang.headerpicker.PicUtils.CHOOSE_HEADER;
import static com.kang.headerpicker.PicUtils.PREVIEW_HEADER;
import static com.kang.utilssdk.ToastUtils.showShort;

/**
 * Created by KangHuiCong on 2017/12/18.
 * E-Mail is 515849594@qq.com
 */
@InjectActivityView(R.layout.header_activity)
public class HeaderChooseActivity extends BaseActivity implements IDialogBottom {


    List<String> list = new ArrayList<>();
    @InjectView(R.id.iv_header)
    CircleImageView ivHeader;

    @Override
    public void success(Object baseModelList) {
    }

    @Override
    public void failure(String error_code, String error_msg) {
    }

    @Override
    public void init() {
        list.add("图库");
        list.add("拍照");
    }


    @OnClick(R.id.iv_header)
    public void onViewClicked() {
        new MyBottomDialog(this, list, this);
    }

    @Override
    public void iDialog(int position) {
        switch (position) {
            case 0:
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, CHOOSE_HEADER);
                break;
            case 1:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSE_HEADER && data != null) {
            //调系统图库，选择返回立即进入裁剪界面
            PicUtils.startPhotoZoom(this, data.getData());
        } else if (requestCode == PREVIEW_HEADER) {
            //裁剪图片并返回地址。按照实际开发情况，在头像上传完了后应该调用
            if (data != null) {
                String path = PicUtils.setPicToView(this, ivHeader, data);
                showShort(path);
//                Glide.with(this).load(path).error(R.drawable.defalut_header).into(ivHeader);

            }
        }
    }

}
