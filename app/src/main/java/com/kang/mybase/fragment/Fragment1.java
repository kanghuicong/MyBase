package com.kang.mybase.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kang.mybase.R;
import com.kang.mybase.activity.MainActivity;
import com.kang.mybase.base.BaseFragment;
import com.kang.mybase.bean.RefreshAllBean;
import com.kang.mybase.custom.view.MyDialog;
import com.kang.mybase.fun.FunData;
import com.kang.mybase.pro.IDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.kang.mybase.fun.ARouterUtil.startHeaderChooseActivity;
import static com.kang.mybase.fun.ARouterUtil.startPhotoChooseActivity;
import static com.kang.mybase.fun.ARouterUtil.startRecyclerActivity;
import static com.kang.mybase.fun.ARouterUtil.startVideoActivity;
import static com.kang.mybase.fun.ARouterUtil.startVoiceActivity;
import static com.kang.mybase.util.httpClient.HttpRequest.getApi;
import static com.kang.utilssdk.ToastUtils.showShort;

/**
 * Created by KangHuiCong on 2017/12/11.
 * E-Mail is 515849594@qq.com
 */
public class Fragment1 extends BaseFragment {

    private FunData funData;

    @Override
    public int setLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment1;
    }

    @Override
    public void init() {
        funData = new FunData((MainActivity) activity, this, this);
    }

    @OnClick({R.id.http_click, R.id.photo_click, R.id.header_click, R.id.voice_click, R.id.video_click, R.id.recycler_click,R.id.update_click, R.id.loading_click})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.http_click://网络请求
                Map<String, Object> map = new HashMap<>();
                funData.getData(getApi().test(map), null);
                break;
            case R.id.photo_click://图片选择
                startPhotoChooseActivity(activity);
                break;
            case R.id.header_click://头像
                startHeaderChooseActivity(activity);
                break;
            case R.id.voice_click://音频
                startVoiceActivity();
                break;
            case R.id.video_click://视频
                startVideoActivity();
                break;
            case R.id.recycler_click://RecyclerView
                startRecyclerActivity();
                break;
            case R.id.update_click://检查更新
                new MyDialog("升级提示", "发现新版本，请及时更新", "下次再说", "立即升级", new IDialog() {
                    @Override
                    public void leftClick(DialogInterface dialog) {
                    }

                    @Override
                    public void rightClick(DialogInterface dialog) {
//                        Intent intent = new Intent(getContext(), UpdateService.class);
//                        intent.putExtra("apkUrl", "http://121.42.53.175:8080/hello_project/resources/upload/TianQiBao201605231.apk");
//                        activity.startService(intent);
                    }
                }).show(activity.getFragmentManager(), "updateDialog");
                break;
            case R.id.loading_click:
                ((MainActivity) activity).showLoading();
                break;
        }
    }

    @Override
    public void success(Object t, String type) {
        RefreshAllBean testAllBean = (RefreshAllBean) t;
        showShort("success:" + testAllBean.getData().get(0).getName());
    }

    @Override
    public void failure(String error_code, String error_msg) {
        showShort("failure:" + error_msg);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
