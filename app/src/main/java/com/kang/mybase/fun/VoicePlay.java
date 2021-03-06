package com.kang.mybase.fun;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.WindowManager;

import com.kang.mybase.service.VoiceService;

import java.io.IOException;

/**
 * Created by KangHuiCong on 2017/12/19.
 * E-Mail is 515849594@qq.com
 */

public class VoicePlay {

    private static MediaPlayer mMediaPlayer;

    public static void beginVoice(final String path) {
        try {
            if (mMediaPlayer == null) mMediaPlayer = new MediaPlayer();
            else {
                mMediaPlayer.stop();
                mMediaPlayer.reset();
            }

            mMediaPlayer.setDataSource(path);
            mMediaPlayer.prepare();

            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayer.start();
                }
            });
        } catch (IOException e) {}

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlaying();
            }
        });
        mMediaPlayer.getCurrentPosition();
    }

    public static void stopPlaying() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public static void startVoiceService(Context context) {
        Intent intentStart = new Intent(context, VoiceService.class);
        //设置音频名字
        VoiceFiles.setFileName("MyVoice_" + System.currentTimeMillis() + ".mp4");
        //开启录音服务
        context.startService(intentStart);
    }


    public static void stopVoiceService(Context context) {
        Intent intentStop = new Intent(context, VoiceService.class);
        //停止录音服务
        context.stopService(intentStop);
    }


}
