package com.kang.mybase.util.inject;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.kang.utilssdk.AssistActivityUtils.assistActivity;


/**
 * Created by KangHuiCong on 2017/12/4.
 * E-Mail is 515849594@qq.com
 */

public final class InjectUtils {
    //butterknife也是注解的方式
    //当时是在练习kotlin，无法使用butterknife，所以自己闲着无聊也写下
    //注解setContentView方法
    public static void injectActivityView(Activity activity) {
        Class clazz = activity.getClass();
        if (clazz.isAnnotationPresent(InjectActivityView.class)) {
            InjectActivityView injectContentView = (InjectActivityView) clazz.getAnnotation(InjectActivityView.class);
            int value = injectContentView.value();
            try {
                Method method = clazz.getMethod("setContentView", int.class);
                method.setAccessible(true);
                method.invoke(activity, value);
                assistActivity(activity.findViewById(android.R.id.content));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public static Class<?> injectModel(Object activity) {
        Class clazz = activity.getClass();
        Field[] fields = clazz.getFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            InjiectModel injiectModel = (InjiectModel) field.getAnnotation(InjiectModel.class);
            if (injiectModel ==null) continue;

            return field.getType();

        }
        return null;
    }

    //注解setOnClickListener方法
    public static void injectClick(final Activity activity) {
        Class clazz = activity.getClass();
        Method[] declaredMethods = clazz.getDeclaredMethods();

        for (int i = 0; i < declaredMethods.length; i++) {
            final Method method = declaredMethods[i];
            InjectClick injectClick = (InjectClick) method.getAnnotation(InjectClick.class);
            if (injectClick == null) continue;

            int[] value = injectClick.value();
            for (int j = 0; j < value.length; j++) {
                int id = value[j];
                final View view = activity.findViewById(id);
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        try {
                            method.invoke(activity, view);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

}
