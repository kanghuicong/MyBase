package com.kang.mybase.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.bumptech.glide.Glide;
import com.kang.headerpicker.CircleImageView;
import com.kang.headerpicker.PicUtils;
import com.kang.headerpicker.SDPathUtils;
import com.kang.mybase.R;
import com.kang.mybase.base.BaseActivity;
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
public class HeaderChooseActivity extends BaseActivity implements IDialogBottom {


    List<String> list = new ArrayList<>();
    List<String> pathList = new ArrayList<>();
    private Uri imageFileUri;

    @InjectView(R.id.iv_header)
    CircleImageView ivHeader;


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
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, CHOOSE_HEADER);
                break;
            case 1://调用摄像头
                String image_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg";
                File file = new File(image_path);
                imageFileUri = Uri.fromFile(file);
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                it.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
                startActivityForResult(it, PicUtils.TAKE_PICTURE_HEADER);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PicUtils.CHOOSE_HEADER && data != null) {
            //调系统图库，选择返回立即进入裁剪界面
            PicUtils.startPhotoZoom(this, data.getData());
        }else if (requestCode == PicUtils.TAKE_PICTURE_HEADER){
            PicUtils.startPhotoZoom(this, imageFileUri);
        } else if (requestCode == PicUtils.PREVIEW_HEADER) {
            //裁剪图片并返回地址。按照实际开发情况，在头像上传完了后应该调用
            if (data != null) {
                String path = PicUtils.getPicPath(this, ivHeader, data);
                Glide.with(this).load(path).into(ivHeader);
                pathList.add(path);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //通常来说，选择完头像后会做上传操作，第二次进来后看到的头像应该是从网络读取的图片
        //所以activity退出后本地的裁剪图片应该清除
        if (!pathList.isEmpty()) {
            for (int i=0;i<pathList.size();i++) {
                SDPathUtils.delFile(pathList.get(i));//删除裁剪地址的图片
            }
        }
    }


    @Override
    public void success(Object baseModelList) {
    }

    @Override
    public void failure(String error_code, String error_msg) {
    }
}
