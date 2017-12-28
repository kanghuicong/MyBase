package com.kang.mybase.base;

import com.kang.mybase.pro.ISubDelete;

/**
 * Created by KangHuiCong on 2017/12/14.
 * E-Mail is 515849594@qq.com
 */

public abstract class BaseData {

    protected ISubDelete iSubDelete;

    public BaseData(ISubDelete iSubDelete) {
        this.iSubDelete = iSubDelete;
    }

}
