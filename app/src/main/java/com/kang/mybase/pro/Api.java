package com.kang.mybase.pro;

import com.kang.mybase.model.TestBean2;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


/**
 * Created by KangHuiCong on 2017/12/13.
 * E-Mail is 515849594@qq.com
 */

public interface Api {

    @POST("entrance/category/list")
    @FormUrlEncoded
    Observable<TestBean2> test(@FieldMap Map<String, Object> map);

}
