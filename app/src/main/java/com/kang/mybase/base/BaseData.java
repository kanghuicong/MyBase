package com.kang.mybase.base;

import com.kang.mybase.pro.Api;
import com.kang.mybase.pro.IHttp;
import com.kang.mybase.pro.ISubDelete;
import com.kang.mybase.util.httpClient.RetrofitClient;

/**
 * Created by KangHuiCong on 2017/12/14.
 * E-Mail is 515849594@qq.com
 */

public abstract class BaseData {
    protected ISubDelete iSubDelete;

    public BaseData(ISubDelete iSubDelete) {
        this.iSubDelete = iSubDelete;
    }

    static Api api;

    public static Api getApi() {
        if (api == null)
            api = RetrofitClient.getRetrofit().create(Api.class);
        return api;
    }


}
