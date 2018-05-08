package com.kang.mybase.fun;

import android.content.Context;

import com.kang.utilssdk.LogUtils;

import java.io.IOException;

/**
 * Created by kanghuicong on 2018/4/27.
 * E-Mail:515849594@qq.com
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static CrashHandler instance = null;

    private CrashHandler() {}

    public static synchronized CrashHandler getInstance() {
        if(instance == null) {
            instance = new CrashHandler();
        }
        return instance;
    }

    public void init(Context context) {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Thread:");
        stringBuilder.append(thread.toString());
        stringBuilder.append("\t");
        stringBuilder.append(ex);
        LogUtils.i(stringBuilder.toString());
    }
}