package com.kang.mybase.fun;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.kang.mybase.base.BaseData;
import com.kang.mybase.model.TestBean;
import com.kang.mybase.pro.IJsonData;
import com.kang.mybase.pro.IHttp;
import com.kang.mybase.pro.ISubDelete;
import com.kang.mybase.util.httpClient.BaseModel;
import com.kang.mybase.util.httpClient.RxHelper;
import com.kang.mybase.util.httpClient.RxSubscribe;


import java.util.Iterator;
import java.util.Map;

import rx.Subscription;

/**
 * Created by KangHuiCong on 2017/12/13.
 * E-Mail is 515849594@qq.com
 */

public class FunData<T> extends BaseData {

    Subscription baseSub;
    IHttp iHttp;

    public FunData(ISubDelete iSubDelete, IHttp iHttp) {
        super(iSubDelete);
        this.iHttp = iHttp;
    }

    public void getData(Map<String, Object> map, final String type) {

        baseSub = getApi().test(map)
                .compose(RxHelper.<T>handleResult())
                .subscribe(new RxSubscribe<T>() {
                    @Override
                    protected void _onNext(T t) {
                        //返回的是Json格式字符串
                        Log.i("khc", t.toString());

//                        iHttp.success(t,type);
                    }
                    @Override
                    protected void _onError(String error_code, String error_msg) {
                        iHttp.failure(error_code, error_msg);
                    }
                });
        iSubDelete.deleteSub(baseSub);
    }


    public void getJsonData(T t, IJsonData iGetData) {
        Gson gson = new Gson();
        String strBase = gson.toJson(t);
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(strBase); // 将json字符串转换成JsonElement
        JsonArray jsonArray = jsonElement.getAsJsonArray(); // 将JsonElement转换成JsonArray
        Iterator it = jsonArray.iterator(); // Iterator处理
        while (it.hasNext()) { // 循环
            jsonElement = (JsonElement) it.next(); // 提取JsonElement
            String json = jsonElement.toString(); // JsonElement转换成String
            iGetData.getJsonData(gson, json);
        }
    }

}
