package com.kang.mybase.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.kang.mybase.fun.FunVoiceFiles;

import java.io.File;
import java.io.IOException;

/**
 * Created by KangHuiCong on 2017/12/19.
 * E-Mail is 515849594@qq.com
 */

public class VoiceService extends Service {

    private static final String TAG = "VoiceService";

    private String mFileName = null;
    private String mFilePath = null;
    private MediaRecorder mRecorder = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "start");
        File folder = new File(new FunVoiceFiles().getPath());
        if (!folder.exists()) folder.mkdir();//新建文件夹

        mFileName = FunVoiceFiles.getFileName();
        mFilePath = FunVoiceFiles.getPath()+"/" + mFileName;
        File file = new File(mFilePath);//新建文件

        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);// 音频源
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);// 音频输出格式
        mRecorder.setOutputFile(mFilePath);//输出文件
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);// 音频格式
        mRecorder.setAudioChannels(1);
        //设置为录制高质量音频
//        mRecorder.setAudioSamplingRate(44100);
//        mRecorder.setAudioEncodingBitRate(192000);

        try {
            mRecorder.prepare();
            mRecorder.start();
            Log.e(TAG, "开始录制！");
        } catch (IOException e) {
            Log.e(TAG, "录制失败！");
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mRecorder != null) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        }
        super.onDestroy();
    }



}
