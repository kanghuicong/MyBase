package com.kang.mybase;

import android.app.Fragment;

import com.kang.mybase.base.BaseFragment;
import com.kang.mybase.util.httpClient.BaseModel;

import java.util.List;

/**
 * Created by KangHuiCong on 2017/12/11.
 * E-Mail is 515849594@qq.com
 */

public class Fragment3 extends BaseFragment {
    @Override
    public int setLayout() {
        return R.layout.fragment3;
    }
    @Override
    public void init() {
//do something...
    }

    @Override
    public void success(Object baseModelList) {

    }

    @Override
    public void failure(String error_code, String error_msg) {

    }
}
