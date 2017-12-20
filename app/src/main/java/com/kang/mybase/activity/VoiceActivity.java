package com.kang.mybase.activity;

import android.content.Intent;
import android.os.Environment;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Chronometer;
import android.widget.ListView;

import com.kang.mybase.R;
import com.kang.mybase.adapter.VoiceAdapter;
import com.kang.mybase.base.BaseActivity;
import com.kang.mybase.fun.FunVoiceFiles;
import com.kang.mybase.fun.FunVoicePlay;
import com.kang.mybase.model.FileBean;
import com.kang.mybase.service.VoiceService;
import com.kang.mybase.util.inject.InjectActivityView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

import static com.kang.mybase.fun.FunVoiceFiles.getAllFiles;

/**
 * Created by KangHuiCong on 2017/12/19.
 * E-Mail is 515849594@qq.com
 */
@InjectActivityView(R.layout.voice_activity)
public class VoiceActivity extends BaseActivity {
    @InjectView(R.id.chronometer)
    Chronometer chronometer;
    @InjectView(R.id.voice_list)
    ListView voiceList;

    private VoiceAdapter voiceAdapter;
    private List<FileBean> list = new ArrayList<FileBean>();

    @Override
    public void init() {
        getVoice();
        getAllFiles(this);
    }

    @OnClick({R.id.voice_start, R.id.voice_stop, R.id.voice_choose,R.id.voice_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.voice_start:
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                Intent intentStart = new Intent(this, VoiceService.class);
                FunVoiceFiles.setFileName("MyVoice_" + System.currentTimeMillis() + ".mp4");
                this.startService(intentStart);
                break;
            case R.id.voice_stop:
                chronometer.stop();
                Intent intentStop = new Intent(this, VoiceService.class);
                this.stopService(intentStop);
                break;
            case R.id.voice_choose:
                getVoice();
                break;
            case R.id.voice_all:
                list.clear();
                list.addAll(FunVoiceFiles.getAllFiles(this));
                voiceAdapter.changeCount(list.size());
                voiceAdapter.notifyDataSetChanged();
                break;
        }
    }

    public void getVoice() {
        //设置文件储存目录
        FunVoiceFiles.setPath(Environment.getExternalStorageDirectory() + "/Kang");
        list.clear();
        list.addAll(FunVoiceFiles.getFiles());
        if(!list.isEmpty()) {
            if (voiceAdapter==null) {
                voiceAdapter = new VoiceAdapter(this, list);
                voiceList.setAdapter(voiceAdapter);
                voiceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        FunVoicePlay.beginVoice(voiceAdapter.getPath(i));
                    }
                });
                voiceList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        FunVoiceFiles.deleteFile(voiceAdapter.getPath(i));
                        list.remove(i);
                        voiceAdapter.changeCount(list.size());
                        voiceAdapter.notifyDataSetChanged();
                        return true;
                    }
                });
            }else {
                voiceAdapter.changeCount(list.size());
                voiceAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FunVoicePlay.stopPlaying();
    }


    @Override
    public void success(Object baseModelList) {
    }

    @Override
    public void failure(String error_code, String error_msg) {
    }
}
