package com.kang.mybase.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kang.mybase.R;
import com.kang.mybase.base.BasePhotoActivity;
import com.kang.mybase.util.inject.InjectActivityView;

import static com.kang.mybase.fun.LoginInterceptor.CHECK_LOGIN;

/**
 * Created by KangHuiCong on 2017/12/12.
 * E-Mail is 515849594@qq.com
 */

@InjectActivityView(R.layout.photo_activity)
@Route(path = "/activity/PhotoChooseActivity",extras = CHECK_LOGIN)
public class PhotoChooseActivity extends BasePhotoActivity {

    @Override
    public void init() {
        //do something......
        //activity继承BasePhotoActivity，本想把BasePhotoActivity写入imagePicker.aar,
        // 但考虑到每个人的adapter有差异，写的太死的话反而限制太多
        //布局加入RecyclerView，id固定为recycler_view
    }


    @Override
    public void success(Object baseModelList,String type) {

    }

    @Override
    public void failure(String error_code, String error_msg) {

    }

    @Override
    public void setItemClicked(int position) {}
}
