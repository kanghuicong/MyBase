package com.kang.mybase.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.ArrayMap;
import android.view.View;

import com.kang.mybase.R;
import com.kang.mybase.activity.HeaderChooseActivity;
import com.kang.mybase.activity.MainActivity;
import com.kang.mybase.activity.PhotoChooseActivity;
import com.kang.mybase.activity.VideoActivity;
import com.kang.mybase.activity.VoiceActivity;
import com.kang.mybase.base.BaseFragment;
import com.kang.mybase.custom.view.MyDialog;
import com.kang.mybase.fun.FunData;
import com.kang.mybase.model.RefreshAllBean;
import com.kang.mybase.pro.IDialog;

import java.util.Map;

import butterknife.OnClick;

import static com.kang.mybase.util.httpClient.HttpRequest.getApi;
import static com.kang.utilssdk.AppUtils.getAppDetailsSettings;
import static com.kang.utilssdk.ToastUtils.showShort;

/**
 * Created by KangHuiCong on 2017/12/11.
 * E-Mail is 515849594@qq.com
 */
public class Fragment1 extends BaseFragment  {

    private FunData funData;

    @Override
    public int setLayout() {
        return R.layout.fragment1;
    }

    @Override
    public void init() {
        funData = new FunData((MainActivity)activity,this, this);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick({R.id.http_click,R.id.photo_click,R.id.header_click,R.id.voice_click,R.id.video_click,R.id.dialog_click,R.id.loading_click})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.http_click://网络请求
                Map<String, Object> map = new ArrayMap<>();
                funData.getData(getApi().test(map),null);
                break;
            case R.id.photo_click://图片选择
                startActivity(new Intent(getActivity(),PhotoChooseActivity.class));
                break;
            case R.id.header_click://头像
                startActivity(new Intent(getActivity(),HeaderChooseActivity.class));
                break;
            case R.id.voice_click://音频
                startActivity(new Intent(getActivity(),VoiceActivity.class));
                break;
            case R.id.video_click://视频
                startActivity(new Intent(getActivity(),VideoActivity.class));
                break;
            case R.id.dialog_click:
                new MyDialog("设置", "是否前往设置权限", "取消", "确定", new IDialog() {
                    @Override
                    public void leftClick(DialogInterface dialog) {}
                    @Override
                    public void rightClick(DialogInterface dialog) {
                        getAppDetailsSettings();
                    }
                }).show(activity.getFragmentManager(), "settingDialog");
                break;
            case R.id.loading_click:
                ((MainActivity)activity).showLoading();
                break;
        }
    }

    @Override
    public void success(Object t,String type) {
        RefreshAllBean testAllBean = (RefreshAllBean)t;
        showShort("success:" + testAllBean.getData().get(0).getName());
    }

    @Override
    public void failure(String error_code, String error_msg) {
        showShort("failure:" + error_msg);
    }


}
