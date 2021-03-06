package com.kang.mybase.fun;

import com.kang.mybase.base.BaseData;
import com.kang.mybase.base.BaseFragmentActivity;
import com.kang.mybase.pro.IHttp;
import com.kang.mybase.pro.ISubDelete;
import com.kang.mybase.util.httpClient.RxHelper;
import com.kang.mybase.util.httpClient.RxSubscribe;


import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by KangHuiCong on 2017/12/13.
 * E-Mail is 515849594@qq.com
 */

public class FunData extends BaseData {

    Subscription baseSub;
    IHttp iHttp;
    BaseFragmentActivity activity;

    public FunData(BaseFragmentActivity activity, ISubDelete iSubDelete, IHttp iHttp) {
        super(iSubDelete);
        this.activity = activity;
        this.iHttp = iHttp;
    }

    public <T> void getData(Observable observable, final String type) {
        if (activity!=null)activity.showLoading();

        baseSub =observable.compose(RxHelper.handleResult())
                .subscribe(new RxSubscribe<T>() {
                    @Override
                    protected void _onNext(T t) {
                        if (activity!=null)activity.dismissLoading();
                        iHttp.success(t,type);
                    }
                    @Override
                    protected void _onError(String error_code, String error_msg) {
                        if (activity!=null)activity.dismissLoading();
                        iHttp.failure(error_code, error_msg);
                    }
                });
        iSubDelete.deleteSub(baseSub);
    }

//    public <T> void getJsonData(T t, IJsonData iGetData) {
//        Gson gson = new Gson();
//        String strBase = gson.toJson(t);
//        JsonParser jsonParser = new JsonParser();
//        JsonElement jsonElement = jsonParser.parse(strBase); // 将json字符串转换成JsonElement
//        JsonArray jsonArray = jsonElement.getAsJsonArray(); // 将JsonElement转换成JsonArray
//        Iterator it = jsonArray.iterator(); // Iterator处理
//        while (it.hasNext()) { // 循环
//            jsonElement = (JsonElement) it.next(); // 提取JsonElement
//            String json = jsonElement.toString(); // JsonElement转换成String
//            iGetData.getJsonData(gson, json);
//        }
//    }

}
