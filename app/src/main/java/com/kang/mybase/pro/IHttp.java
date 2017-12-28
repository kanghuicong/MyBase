package com.kang.mybase.pro;
/**
 * Created by KangHuiCong on 2017/12/13.
 * E-Mail is 515849594@qq.com
 */

public interface IHttp<T> {
    void success(T baseModelList,String type);

    void failure(String error_code, String error_msg);

}
