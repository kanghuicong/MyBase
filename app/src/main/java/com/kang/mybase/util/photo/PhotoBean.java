package com.kang.mybase.util.photo;

import com.litao.android.lib.entity.PhotoEntry;

import java.util.List;

/**
 * Created by KangHuiCong on 2017/7/31.
 * e-mail:515849594@qq.com
 */

public class PhotoBean {
    public static final int  RECEIVED_PHOTOS_ID = 0x00000010;

    public static final int  SELECTED_PHOTOS_ID = 0x00000020;


    public List<PhotoEntry> photos;
    public int id;

    public PhotoBean(List<PhotoEntry> photos, int id){
        this.photos = photos;
        this.id = id;
    }
}
