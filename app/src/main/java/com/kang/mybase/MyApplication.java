package com.kang.mybase;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kang.mybase.custom.other.InitializeService;
import com.kang.mybase.fun.CrashHandler;
import com.kang.utilssdk.constant.BaseApplication;


/**
 * Created by KangHuiCong on 2017/12/11.
 * E-Mail is 515849594@qq.com
 */

public class MyApplication extends BaseApplication {

    public static String UserId = "";
    public final static String TEST_URL = "http://118.178.136.110/api/";

    @Override
    public void _onCreate() {

        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)

        ARouter.init(this);
        InitializeService.start(this);//第三方初始化操作

//        CrashHandler.getInstance().init(this);

    }


}
