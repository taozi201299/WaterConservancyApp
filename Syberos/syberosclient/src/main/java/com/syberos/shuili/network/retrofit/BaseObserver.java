package com.syberos.shuili.network.retrofit;

import com.syberos.shuili.utils.LogUtils;
import com.syberos.shuili.utils.NetworkUtils;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public class BaseObserver<T> implements Observer<T> {
    T t;
    protected String errMsg = "";
    protected Disposable disposable;

    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
//        Object.class.getClass().toString()
        RxApiManager.get().add(t,d);
    }

    @Override
    public void onNext(T t) {}

    @Override
    public void onError(Throwable e) {
        LogUtils.d("Subscriber onError", e.getMessage());
        if (!NetworkUtils.isConnected()) {
            errMsg = "网络连接出错,";
//        } else if (e instanceof APIException) {
//            APIException exception = (APIException) e;
//            errMsg = exception.getMessage() + ", ";
        } else if (e instanceof HttpException) {
            errMsg = "网络请求出错,";
        } else if (e instanceof IOException) {
            errMsg = "网络出错,";
        }

        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void onComplete() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
