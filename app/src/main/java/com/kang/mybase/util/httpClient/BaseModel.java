package com.kang.mybase.util.httpClient;

import java.io.Serializable;

/**
 * Created by KangHuiCong on 2017/7/24.
 * e-mail:515849594@qq.com
 */

public class BaseModel<T> implements Serializable {
    String error_code;
    String error_msg;
    public T data;
    //统一数据格式error_code，error_msg，data(请根据实际情况修改RxHelper)
    //统一格式，封装RxHelper类
    //data里为返回的数据，如：
    //    {
    //        "error_code": "0",
    //        "error_msg": "ok",
    //        "data": [
    //        {
    //            "category_id": 1,
    //            "name": "音乐类",
    //            "description": ""
    //        }
    //    ]
    //    }

    public T getData() {
        return data;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean success(){
        //在本url，当数据返回成功时，error_code为"0"，请根据实际修改
        return error_code.equals("0");
    }

//    @Override
//    public String toString() {
//        return "BaseError{" +
//                "data=" + data +
//                ", error_code='" + error_code + '\'' +
//                ", error_msg='" + error_msg + '\'' +
//                '}';
//    }
}
