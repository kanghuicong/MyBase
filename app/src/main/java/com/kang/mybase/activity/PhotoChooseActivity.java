package com.kang.mybase.activity;

import com.kang.mybase.R;
import com.kang.mybase.util.photo.BasePhotoActivity;
import com.kang.mybase.util.inject.InjectActivityView;

/**
 * Created by KangHuiCong on 2017/12/12.
 * E-Mail is 515849594@qq.com
 */

@InjectActivityView(R.layout.photo_activity)
public class PhotoChooseActivity extends BasePhotoActivity {

    @Override
    public void init() {
        //do something......
    }


    @Override
    public void success(Object baseModelList) {

    }

    @Override
    public void failure(String error_code, String error_msg) {

    }

}
