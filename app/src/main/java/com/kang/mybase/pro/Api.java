package com.kang.mybase.pro;

import com.kang.mybase.bean.RefreshAllBean;
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
    Observable<RefreshAllBean> test(@FieldMap Map<String, Object> map);

//    Observable<RefreshAllBean> tests(@Query("type") String type, @Query("postid") String postid);
}
