package com.kang.mybase.data;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kang.mybase.base.BaseData;
import com.kang.mybase.model.TestBean;
import com.kang.mybase.pro.IGetData;
import com.kang.mybase.pro.IHttp;
import com.kang.mybase.pro.ISubDelete;
import com.kang.mybase.util.httpClient.BaseModel;
import com.kang.mybase.util.httpClient.HttpRequest;
import com.kang.mybase.util.httpClient.RxHelper;
import com.kang.mybase.util.httpClient.RxSubscribe;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rx.Subscription;

/**
 * Created by KangHuiCong on 2017/12/13.
 * E-Mail is 515849594@qq.com
 */

public class TestData<T> extends BaseData {

    Subscription baseSub;
    IHttp iHttp;

    public TestData(ISubDelete iSubDelete, IHttp iHttp) {
        super(iSubDelete);
        this.iHttp = iHttp;
    }

    public void getTestData() {
        //map 为请求参数，键值对形式
        Map<String, Object> map = new HashMap<>();

        baseSub = getApi().test(map)
                .compose(RxHelper.<T>handleResult())
                .subscribe(new RxSubscribe<T>() {
                    @Override
                    protected void _onNext(T t) {
                        iHttp.success(t);
                    }

                    @Override
                    protected void _onError(String error_code, String error_msg) {
                        iHttp.failure(error_code, error_msg);
                    }
                });
        iSubDelete.deleteSub(baseSub);
    }


    public void getListData(T t, IGetData iGetData) {
        Gson gson = new Gson();
        String strBase = gson.toJson(t);
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(strBase); // 将json字符串转换成JsonElement
        JsonArray jsonArray = jsonElement.getAsJsonArray(); // 将JsonElement转换成JsonArray
        Iterator it = jsonArray.iterator(); // Iterator处理
        while (it.hasNext()) { // 循环
            jsonElement = (JsonElement) it.next(); // 提取JsonElement
            String json = jsonElement.toString(); // JsonElement转换成String
            iGetData.getData(gson, json);
        }
    }

}
