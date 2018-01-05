package com.kang.mybase.util.httpClient;


import com.kang.mybase.MyApplication;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.kang.utilssdk.Utils.getApp;

public class RetrofitClient {
    RetrofitClient() {}

    public static Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                //设置OKHttpClient
                .client(OKHttpFactory.getOkHttpClient())
                //baseUrl
                .baseUrl(MyApplication.TEST_URL)
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

}
