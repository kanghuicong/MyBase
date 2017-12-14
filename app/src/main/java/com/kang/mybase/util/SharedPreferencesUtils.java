package com.kang.mybase.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by KangHuiCong on 2017/12/7.
 * E-Mail is 515849594@qq.com
 */

public final class SharedPreferencesUtils {

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("com.util", Context.MODE_PRIVATE);
    }

    public static void putPreferences(Context context, String key, boolean value) {
        SharedPreferences SharedPreferences = getSharedPreferences(context);
        android.content.SharedPreferences.Editor editor = SharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getPreferences(Context context, String key,
                                         boolean defaultValue) {
        return getSharedPreferences(context).getBoolean(key, defaultValue);
    }

}
