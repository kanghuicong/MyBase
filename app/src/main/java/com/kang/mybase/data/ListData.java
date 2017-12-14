package com.kang.mybase.data;

import com.kang.mybase.base.BaseData;
import com.kang.mybase.pro.IHttp;
import com.kang.mybase.pro.ISubDelete;
import com.kang.mybase.util.httpClient.BaseModel;
import com.kang.mybase.util.httpClient.RxHelper;
import com.kang.mybase.util.httpClient.RxSubscribe;

import java.util.List;

import rx.Observable;
import rx.Subscription;

/**
 * Created by KangHuiCong on 2017/12/14.
 * E-Mail is 515849594@qq.com
 */

public class ListData extends BaseData{

    Subscription baseSub;

    public ListData(ISubDelete iSubDelete) {
        super(iSubDelete);
    }

    public void getListData(Observable observable) {
        baseSub = observable.compose(RxHelper.<List<BaseModel>>handleResult()).subscribe(new RxSubscribe<List<BaseModel>>() {
            @Override
            protected void _onNext(List<BaseModel> baseModel) {

            }
            @Override
            protected void _onError(String error_code, String error_msg) {

            }
            @Override
            public void onCompleted() {}
        });
    }
}
