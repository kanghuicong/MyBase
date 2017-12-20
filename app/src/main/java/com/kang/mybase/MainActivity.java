package com.kang.mybase;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.kang.mybase.base.BaseFragmentActivity;
import com.kang.mybase.receiver.NetBroadcastReceiver;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kang.utilssdk.AssistActivityUtils.assistActivity;

/**
 * Created by KangHuiCong on 2017/12/11.
 * E-Mail is 515849594@qq.com
 */

public class MainActivity extends BaseFragmentActivity {
    private FragmentManager fm;
    private long touchTime = 0;

    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assistActivity(findViewById(android.R.id.content));
        setContentView(R.layout.main_activity);
        ButterKnife.inject(this);

        //因为静态注册的广播在程序退出后依然有效，所以在退出的时候设置COMPONENT_ENABLED_STATE_DISABLED
        //setComponentEnabledSetting方法相当于修改Manifest里注册的NetBroadcastReceiver的enable属性（默认true）
        //所以APP进入要还原重置该属性
        getPackageManager().setComponentEnabledSetting(
                new ComponentName("com.kang.mybase", NetBroadcastReceiver.class.getName()),
                PackageManager.COMPONENT_ENABLED_STATE_DEFAULT,
                PackageManager.DONT_KILL_APP);

        fm = getSupportFragmentManager();
        fragment1 = new Fragment1();
        fm.beginTransaction().replace(R.id.fl_main, fragment1).addToBackStack(null).commit();
    }

    @OnClick({R.id.rb1, R.id.rb2, R.id.rb3, R.id.rb4})
    public void onClick(View view) {
        FragmentTransaction transaction = fm.beginTransaction();
        hideAllFragment(transaction);

        switch (view.getId()) {
            case R.id.rb1:
                if (fragment1 == null)
                    transaction.add(R.id.fl_main, fragment1 = new Fragment1());
                else transaction.show(fragment1);
                break;
            case R.id.rb2:
                if (fragment2 == null)
                    transaction.add(R.id.fl_main, fragment2 = new Fragment2());
                else transaction.show(fragment2);
                break;
            case R.id.rb3:
                if (fragment3 == null)
                    transaction.add(R.id.fl_main, fragment3 = new Fragment3());
                else transaction.show(fragment3);
                break;
            case R.id.rb4:
                if (fragment4 == null)
                    transaction.add(R.id.fl_main, fragment4 = new Fragment4());
                else transaction.show(fragment4);
                break;
        }
        transaction.commit();
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        if (fragment1 != null) transaction.hide(fragment1);
        if (fragment2 != null) transaction.hide(fragment2);
        if (fragment3 != null) transaction.hide(fragment3);
        if (fragment4 != null) transaction.hide(fragment4);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - touchTime) >= 2000) {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                touchTime = currentTime;
            } else {
                //确认fragment的Subscription被取消订阅
                if (fragment1 != null && fragment1.baseSub!=null && !fragment1.baseSub.isUnsubscribed()) fragment1.baseSub.unsubscribe();
                if (fragment2 != null && fragment2.baseSub!=null && !fragment2.baseSub.isUnsubscribed()) fragment3.baseSub.unsubscribe();
                if (fragment3 != null && fragment3.baseSub!=null && !fragment3.baseSub.isUnsubscribed()) fragment3.baseSub.unsubscribe();
                if (fragment4 != null && fragment4.baseSub!=null && !fragment4.baseSub.isUnsubscribed()) fragment4.baseSub.unsubscribe();
                finish();
                //关闭实时监控网络情况广播
                getPackageManager().setComponentEnabledSetting(
                        new ComponentName("com.kang.mybase",
                                NetBroadcastReceiver.class.getName()),
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.DONT_KILL_APP);
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}