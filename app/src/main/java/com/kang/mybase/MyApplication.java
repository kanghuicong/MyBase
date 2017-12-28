package com.kang.mybase;

import com.kang.mybase.fun.InitializeService;
import com.kang.mybase.pro.Api;
import com.kang.mybase.util.httpClient.RetrofitClient;
import com.kang.utilssdk.constant.BaseApplication;


/**
 * Created by KangHuiCong on 2017/12/11.
 * E-Mail is 515849594@qq.com
 */

public class MyApplication extends BaseApplication {

    public final static String TEST_URL = "http://118.178.136.110/api/";

    @Override
    public void _onCreate() {
        InitializeService.start(this);//第三方初始化操作
    }


}
