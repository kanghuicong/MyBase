package com.kang.mybase;

import android.app.Application;
import com.kang.mybase.base.InitializeService;

import static com.kang.mybase.util.Utils.init;

/**
 * Created by KangHuiCong on 2017/12/11.
 * E-Mail is 515849594@qq.com
 */

public class MyApplication extends Application {

    public final static String TEST_URL = "http://118.178.136.110/api/";

    @Override
    public void onCreate() {
        super.onCreate();
        init(this);//初始化Utils
        InitializeService.start(this);//第三方初始化操作

    }
}
