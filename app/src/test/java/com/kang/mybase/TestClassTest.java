package com.kang.mybase;

import com.kang.mybase.bean.FileBean;
import com.kang.utilssdk.LogUtils;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kanghuicong on 2018/4/27.
 * E-Mail:515849594@qq.com
 */
public class TestClassTest {
    TestClass testClass;

    @Before
    public void setUp() throws Exception {
        testClass = new TestClass();
    }

    @Test
    public void test1() throws Exception {
        testClass.test();
    }

}