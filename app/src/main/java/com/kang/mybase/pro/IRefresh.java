package com.kang.mybase.pro;

/**
 * Created by KangHuiCong on 2018/1/3.
 * E-Mail is 515849594@qq.com
 */

public interface IRefresh<T> {

    void refreshSuccess(T baseModelList);

    void refreshFailure(String error_code, String error_msg);

    void loadSuccess(T baseModelList);

    void loadFailure(String error_code, String error_msg);
}

