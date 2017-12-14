package com.kang.mybase.pro;

import com.kang.mybase.model.TestBean;
import com.kang.mybase.util.httpClient.BaseModel;

import java.util.List;

import rx.Subscription;

/**
 * Created by KangHuiCong on 2017/12/13.
 * E-Mail is 515849594@qq.com
 */

public interface IHttp<T> {
    void success(T baseModelList);

    void failure(String error_code, String error_msg);

}
