package com.kang.mybase.util.httpClient;

import android.util.Log;

import com.kang.mybase.pro.Api;

public class HttpRequest {

    static Api api;

    public static Api getApi() {
        if (api == null)
            synchronized (HttpRequest.class) {
                api = RetrofitClient.getRetrofit().create(Api.class);
            }
        return api;
    }

}
