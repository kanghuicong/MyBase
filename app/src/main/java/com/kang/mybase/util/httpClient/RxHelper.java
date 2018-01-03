package com.kang.mybase.util.httpClient;


import android.util.Log;

import com.kang.mybase.model.BaseBean;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.kang.utilssdk.ToastUtils.showShort;

public class RxHelper {

//    public static <T> Observable.Transformer<BaseModel<T>, T> handleBaseResult() {
//        return new Observable.Transformer<BaseModel<T>, T>() {
//            @Override
//            public Observable<T> call(Observable<BaseModel<T>> tObservable) {
//                return tObservable.flatMap(new Func1<BaseModel<T>, Observable<T>>() {
//                    @Override
//                    public Observable<T> call(BaseModel<T> result) {
//                        Log.i("result--", result.toString());
//                        if (result.success()) {
//                            return createData(result.data);
//                        } else {
//                            return Observable.error(new ServerException(result.error_code, result.error_msg));
//                        }
//                    }
//                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//            }
//        };
//    }

    public static <T> Observable.Transformer<T, T> handleResult() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.flatMap(new Func1<T, Observable<T>>() {
                    @Override
                    public Observable<T> call(T result) {
                        if (((BaseBean) result).getError_code().equals("0")) {
                            return createData(result);
                        } else {
                            return Observable.error(new ServerException(((BaseBean) result).error_code, ((BaseBean) result).error_msg));
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                    subscriber.onCompleted();
                }
            }
        });
    }

}
