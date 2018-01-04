package com.kang.mybase.fun;

import com.kang.mybase.base.BaseData;
import com.kang.mybase.custom.MyRefresh;
import com.kang.mybase.model.BaseBean;
import com.kang.mybase.pro.IRefresh;
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

public class RefreshData extends BaseData {

    Subscription baseSub;
//    BaseFragmentActivity activity;
    IRefresh iRefresh;
    MyRefresh myRefresh;
    public RefreshData(MyRefresh myRefresh,ISubDelete iSubDelete) {
        super(iSubDelete);
//        this.activity = activity;
//        this.iRefresh = iRefresh;
        this.myRefresh = myRefresh;
    }

    public <T> void getRefreshData(Observable observable, final IRefresh iRefresh) {
//        if (activity!=null)activity.showLoading();

        baseSub =observable.compose(RxHelper.handleResult())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscribe<BaseBean<T>>() {
                    @Override
                    protected void _onNext(BaseBean<T> t) {
//                        if (activity!=null)activity.dismissLoading();
                        myRefresh.setRefreshState(1);
                        iRefresh.refreshSuccess(t);
                    }
                    @Override
                    protected void _onError(String error_code, String error_msg) {
//                        if (activity!=null)activity.dismissLoading();
                        myRefresh.setRefreshState(0);
                        iRefresh.refreshFailure(error_code, error_msg);
                    }
                });
        iSubDelete.deleteSub(baseSub);
    }


    public  <T>void getLoadData(Observable observable, final IRefresh iRefresh) {
//        if (activity!=null)activity.showLoading();

        baseSub =observable.compose(RxHelper.handleResult())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscribe<BaseBean<T>>() {
                    @Override
                    protected void _onNext(BaseBean<T> t) {
//                        if (activity!=null)activity.dismissLoading();
                        if (t.getError_code().equals("0")) myRefresh.setLoadState(1);
                        else if (t.getError_code().equals("1")) myRefresh.setLoadState(2);
                        iRefresh.loadSuccess(t);
                    }
                    @Override
                    protected void _onError(String error_code, String error_msg) {
//                        if (activity!=null)activity.dismissLoading();
                        myRefresh.setLoadState(0);
                        iRefresh.loadFailure(error_code, error_msg);
                    }
                });
        iSubDelete.deleteSub(baseSub);
    }

}
