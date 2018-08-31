package com.syberos.shuili.retrofit;

import android.util.Log;

import com.syberos.shuili.entity.thematic.acci.AcciEntry;
import com.syberos.shuili.entity.thematic.hidden.HiddenEntry;
import com.syberos.shuili.entity.thematic.hidden.HiddenEntryTest;
import com.syberos.shuili.entity.thematic.wins.WinsEntry;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by BZB on 2018/7/13.
 * Project: Syberos.
 * Package：com.example.testmodule.testrxjavaretrofit.
 */
public class RetrofitHttpMethods {
    private static final String BASE_URL = "http://192.168.1.11:7080/";
    public static final int TIME_OUT = 5;
    private Retrofit retrofit;
    private RetrofitApiService retrofitApiService;

    private RetrofitHttpMethods() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.e("RetrofitLog", "retrofitBack = " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient();
        client.newBuilder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
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
    public void getThematicHidden(Observer<HiddenEntry> observer, String sourceType,
                                  String orgGuid,
                                  String startTime,
                                  String endTime) {
        retrofitApiService.getThematicHidden(sourceType, orgGuid, startTime, endTime)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
    /**
     * 获取事故数据
     */
    public void getThematicAcci(Observer<AcciEntry> observer, String sourceType,
                                String orgGuid,
                                String startTime,
                                String endTime){
        retrofitApiService.getThematicAcci(sourceType,orgGuid,startTime,endTime)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 获取水利稽查数据
     */
    public void getThematicWins(Observer<WinsEntry> observer, String sourceType,
                                String orgGuid,
                                String subSourceType,
                                String startTime,
                                String endTime) {
        retrofitApiService.getThematicWins(sourceType, orgGuid, subSourceType, startTime, endTime)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
