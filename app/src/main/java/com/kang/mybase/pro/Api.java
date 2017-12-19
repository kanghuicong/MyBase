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
    //通常数据请求的接口只有一个，只需改变map参数即可
    //如果有多个接口，则一个接口对应一个Observable，照着上面写就行
    //并修改FunData.getData(Observable observable,Map<String, Object> map)
    //将其作为参数外部传入
}
