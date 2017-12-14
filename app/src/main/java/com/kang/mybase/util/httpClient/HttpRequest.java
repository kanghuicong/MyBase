package com.kang.mybase.util.httpClient;

import com.kang.mybase.pro.Api;

public class HttpRequest {

    static Api api;

    public static Api getApi() {
        if (api == null)
            api = RetrofitClient.getRetrofit().create(Api.class);
        return api;
    }

}
