package com.kang.mybase.activity;

import android.os.Environment;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Chronometer;
import android.widget.ListView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kang.mybase.R;
import com.kang.mybase.base.BaseMyAdapter;
import com.kang.mybase.adapter.VoiceAdapter;
import com.kang.mybase.base.BaseActivity;
import com.kang.mybase.fun.VoiceFiles;
import com.kang.mybase.fun.VoicePlay;
import com.kang.mybase.bean.FileBean;
import com.kang.mybase.util.inject.InjectActivityView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

import static com.kang.mybase.fun.VoiceFiles.getAllFiles;
import static com.kang.utilssdk.ToastUtils.showShort;

/**
 * Created by KangHuiCong on 2017/12/19.
 * E-Mail is 515849594@qq.com
 */
@InjectActivityView(R.layout.voice_activity)
@Route(path = "/activity/VoiceActivity")
public class VoiceActivity extends BaseActivity {
    @InjectView(R.id.chronometer)
    Chronometer chronometer;
    @InjectView(R.id.voice_list)
    ListView voiceList;

    private BaseMyAdapter voiceAdapter;
    private List<FileBean> list = new ArrayList<FileBean>();

    @Override
    public void init() {
        getVoice();
    }

    @OnClick({R.id.voice_start, R.id.voice_stop, R.id.voice_choose, R.id.voice_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.voice_start://开始录音
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();//开始计时
                VoicePlay.startVoiceService(this);
                break;
            case R.id.voice_stop://停止录音
                chronometer.stop();//停止计时
                VoicePlay.stopVoiceService(this);
                break;
            case R.id.voice_choose://选择录音文件
                getVoice();
                break;
            case R.id.voice_all://查看音频文件
                if (getAllFiles(this) != null) {
                    list.clear();
                    list.addAll(getAllFiles(this));

                    if (voiceAdapter == null) {
                        voiceAdapter = new BaseMyAdapter(new VoiceAdapter(this));
                        voiceList.setAdapter(voiceAdapter);
                    }
                    voiceAdapter.refreshData(list);
                }else showShort("无音频文件");
                break;
        }
    }

    public void getVoice() {
        //设置文件储存目录
        VoiceFiles.setPath(Environment.getExternalStorageDirectory() + "/Kang");
        list.clear();
        list.addAll(VoiceFiles.getFiles());
        if (!list.isEmpty()) {
            if (voiceAdapter == null) {
                voiceAdapter = new BaseMyAdapter(new VoiceAdapter(this));
                voiceList.setAdapter(voiceAdapter);
                voiceAdapter.refreshData(list);

                voiceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        VoicePlay.beginVoice(((FileBean)voiceAdapter.getItem(i)).getPath());
                    }
                });
                voiceList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        VoiceFiles.deleteFile(((FileBean)voiceAdapter.getItem(i)).getPath());
                        list.remove(i);
                        voiceAdapter.refreshData(list);
                        return true;
                    }
                });
            } else {
                voiceAdapter.refreshData(list);
            }
        }else {
            showShort("无录音文件");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VoicePlay.stopPlaying();//关闭MediaPlayer
    }


    @Override
    public void success(Object baseModelList, String type) {
    }

    @Override
    public void failure(String error_code, String error_msg) {
    }
}
