package com.kang.mybase.fun;

import android.app.Activity;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.kang.mybase.model.FileBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KangHuiCong on 2017/12/19.
 * E-Mail is 515849594@qq.com
 */

public final class FunVoiceFiles {
    public static String path;
    public static String fileName;

    public static ArrayList<FileBean> getFiles() {
        if (path!=null) {
            ArrayList<FileBean> fileList = new ArrayList<FileBean>();
            File folder = new File(path);
            File[] files = folder.listFiles();

            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    File file = files[i];
                    String fileName = file.getName();
                    String filePath = file.getPath();
                    if (fileName.endsWith(".mp4")) {
                        FileBean fileBean = new FileBean();
                        fileBean.setName(fileName);
                        fileBean.setPath(filePath);
                        fileList.add(fileBean);
                    }
                }
            }
            return fileList;
        } else return null;
    }

    public static void deleteFile(String path) {
        File file = new File(path);
        file.delete();
    }

    public static List<FileBean> getAllFiles(Activity activity) {
        List<FileBean> list = new ArrayList<FileBean>();
        Cursor cursor = activity.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        cursor.moveToFirst();
        int counter = cursor.getCount();

        for(int j = 0 ; j < counter; j++){
            String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
            String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));

            FileBean fileBean = new FileBean();
            fileBean.setName(name);
            fileBean.setPath(path);
            list.add(fileBean);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public static void setPath(String path) {
        FunVoiceFiles.path = path;
    }

    public static String getPath() {
        return path;
    }

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        FunVoiceFiles.fileName = fileName;
    }
}
