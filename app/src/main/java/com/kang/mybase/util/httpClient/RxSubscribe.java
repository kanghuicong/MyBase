package com.kang.mybase.util.httpClient;


import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.kang.utilssdk.NetworkUtils;

import org.json.JSONException;

import java.net.ConnectException;

import rx.Subscriber;


public abstract class RxSubscribe<T> extends Subscriber<T> {
    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onCompleted() {
        this.unsubscribe();//取消订阅
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof ServerException) {
            if (!NetworkUtils.isConnected()) {
                _onError("101", "网络不可用");
            } else if (e instanceof JsonParseException
                    || e instanceof JSONException
                    || e instanceof ParseException) {
                _onError("102", "解析错误");
            } else if (e instanceof ConnectException) {
                _onError("103", "连接失败");
            } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
                _onError("104", "证书验证失败");
            } else if (e instanceof ServerException) {
                //如果返回的错误信息是我们与后台定义好的ServerException格式
                //则返回code与message
                /*{
                *    code:1001,
                *    message:"发生错误"
                * }*/
                _onError(((ServerException) e).getCode(), e.getMessage());
            }
        }
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String error_code, String error_msg);


}
