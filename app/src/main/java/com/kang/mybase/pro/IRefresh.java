package com.kang.mybase.pro;

import java.util.List;

import rx.Observable;

/**
 * Created by KangHuiCong on 2018/1/3.
 * E-Mail is 515849594@qq.com
 */

public interface IRefresh<T> {

    Observable refreshObservable();

    Observable loadObservable();

    void refreshSuccess(T baseModelList);

//    void refreshFailure(String error_code, String error_msg);

    void loadSuccess(T baseModelList);

//    void loadFailure(String error_code, String error_msg);





}

