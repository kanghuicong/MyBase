package com.kang.mybase.pro;


import com.kang.mybase.model.TestBean;
import com.kang.mybase.util.httpClient.BaseModel;

import java.util.List;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


/**
 * Created by KangHuiCong on 2017/12/13.
 * E-Mail is 515849594@qq.com
 */

public interface Api<T> {

    @POST("entrance/category/list")
    @FormUrlEncoded
    Observable<BaseModel> test(@FieldMap Map<String, Object> map);
//    Observable<BaseModel<List<TestBean>>> test(@FieldMap Map<String, Object> map);
//    如果url后面要拼接参数，则在TestData里用Map键值对的方式传入
}
