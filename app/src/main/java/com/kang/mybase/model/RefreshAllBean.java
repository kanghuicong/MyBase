package com.kang.mybase.model;

import com.kang.mybase.base.BaseBean;

import java.util.List;

/**
 * Created by KangHuiCong on 2017/12/27.
 * E-Mail is 515849594@qq.com
 */

public class RefreshAllBean extends BaseBean {

    List<RefreshItemBean> data;

    public List<RefreshItemBean> getData() {
        return data;
    }

    public void setData(List<RefreshItemBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RefreshAllBean{" +
                "data=" + data +
                '}';
    }
}
