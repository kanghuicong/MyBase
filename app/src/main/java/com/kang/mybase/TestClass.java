package com.kang.mybase;


import android.content.Intent;
import android.graphics.BitmapRegionDecoder;
import android.test.InstrumentationTestCase;

import com.kang.mybase.bean.FileBean;

import java.util.ArrayList;
import java.util.List;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

/**
 * Created by kanghuicong on 2018/4/27.
 * E-Mail:515849594@qq.com
 */

public class TestClass {

    public void test() throws Exception{
        List<FileBean> list = new ArrayList<>();
        for (int i=0;i<5;i++){
            FileBean fileBean = new FileBean();
            fileBean.setName(i+"");
            list.add(fileBean);
        }

        System.out.print("list:"+list.toString()+"\n");

        FileBean fileBean = new FileBean();
        List<FileBean> list2 = new ArrayList<>();
        for (int i=0;i<5;i++){
            fileBean.setName(i+"");
            list2.add(fileBean);
        }
        System.out.print("list2:"+list2.toString());

//        PathClassLoader;
//        DexClassLoader;
//        DexPathList;
//        Element;
    }

}
