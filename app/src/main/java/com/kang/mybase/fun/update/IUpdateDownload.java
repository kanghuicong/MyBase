package com.kang.mybase.fun.update;

/**
 * Created by kanghuicong on 2018/4/9.
 * E-Mail:515849594@qq.com
 */

public interface IUpdateDownload {
    void onStarted();

    void onProgressChanged(int progress, String downloadUrl);

    void onFinished(float completeSize, String downloadUrl);

    void onFailure();
}
