package com.kang.mybase.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.kang.mybase.base.BaseActivity;
import com.kang.mybase.base.BaseFragmentActivity;
import com.kang.mybase.pro.INetChange;
import com.kang.mybase.util.NetworkUtils;

/**
 * Created by KangHuiCong on 2017/12/12.
 * E-Mail is 515849594@qq.com
 */

public class NetBroadcastReceiver extends BroadcastReceiver{
    //根据实际需求选择是否需要该广播
    //实时监控网络变化，回调-->BaseFragmentActivity
    //需在清单里注册
    INetChange iNetChange = BaseFragmentActivity.iNetChange;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == ConnectivityManager.CONNECTIVITY_ACTION) {
            NetworkUtils.NetworkType netWorkState = NetworkUtils.getNetworkType();
            if (iNetChange!=null)iNetChange.onNetChange(netWorkState);
        }
    }
}
