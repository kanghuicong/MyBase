package com.kang.mybase.model;

import java.util.List;

/**
 * Created by KangHuiCong on 2017/12/27.
 * E-Mail is 515849594@qq.com
 */

public class TestBean2 extends BaseBean {

    List<TestBean> data;

    public List<TestBean> getData() {
        return data;
    }

    public void setData(List<TestBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TestBean2{" +
                "data=" + data +
                '}';
    }
}
