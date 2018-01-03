package com.kang.mybase.base;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.bumptech.glide.Glide;
import com.kang.headerpicker.PicUtils;
import com.kang.headerpicker.SDPathUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.kang.headerpicker.PicUtils.CHOOSE_HEADER;

/**
 * Created by KangHuiCong on 2018/1/3.
 * E-Mail is 515849594@qq.com
 */

public abstract class BaseHeaderActivity extends BaseActivity{
    private Uri imageFileUri;
    private List<String> pathList = new ArrayList<>();

    public abstract void getUrl(String url);

    public void intoCamera() {
        String image_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg";
        File file = new File(image_path);
        imageFileUri = Uri.fromFile(file);
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        it.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
        startActivityForResult(it, PicUtils.TAKE_PICTURE_HEADER);
    }

    public void intoGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, CHOOSE_HEADER);
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
                getUrl(PicUtils.getPicPath(data));
                pathList.add(PicUtils.getPicPath(data));
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
    public void init() {}

    @Override
    public void success(Object baseModelList, String type) {}

    @Override
    public void failure(String error_code, String error_msg) {}

}
