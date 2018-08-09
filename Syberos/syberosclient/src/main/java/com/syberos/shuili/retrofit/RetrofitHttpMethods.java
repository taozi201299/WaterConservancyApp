package com.syberos.shuili.retrofit;

import com.syberos.shuili.entity.thematicchart.accident.AccidentDetailEntry;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by BZB on 2018/7/13.
 * Project: Syberos.
 * Package：com.example.testmodule.testrxjavaretrofit.
 */
public class RetrofitHttpMethods {
    private static final String BASE_URL = "http://api.laifudao.com/open/";
    public static final int TIME_OUT = 5;
    private Retrofit retrofit;
    private RetrofitApiService retrofitApiService;

    private RetrofitHttpMethods() {
        OkHttpClient client = new OkHttpClient();
        client.newBuilder().connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        retrofitApiService = retrofit.create(RetrofitApiService.class);

    }

    private static RetrofitHttpMethods instance;

    public static RetrofitHttpMethods getInstance() {
        if (instance == null) {
            synchronized (RetrofitHttpMethods.class) {
                if (instance == null) {
                    instance = new RetrofitHttpMethods();
                }
            }
        }
        return instance;
    }

    /**
     * 获取Joke数据
     *
     * @param observer
     */
    public void getAccidentDetail(Observer<AccidentDetailEntry> observer) {

        retrofitApiService.getAccidentDetail()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
