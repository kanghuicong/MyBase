package com.kang.mybase.fun;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by KangHuiCong on 2017/12/19.
 * E-Mail is 515849594@qq.com
 */

public final class FunVoiceFiles {
    public static String path;
    public static String fileName;

    public static ArrayList<File> getFiles() {
        if (path!=null) {
            ArrayList<File> fileList = new ArrayList<File>();
            File folder = new File(path);
            File[] files = folder.listFiles();

            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    File file = files[i];
                    String fileName = file.getName();
                    if (fileName.endsWith(".mp4")) {
                        fileList.add(file);
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
