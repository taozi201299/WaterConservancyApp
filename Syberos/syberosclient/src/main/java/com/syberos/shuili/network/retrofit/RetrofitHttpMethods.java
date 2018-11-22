package com.syberos.shuili.network.retrofit;

import android.util.Log;

import com.syberos.shuili.entity.securitycloud.SecurityCloudAreaEntry;
import com.syberos.shuili.entity.securitycloud.SecurityCloudOrgEntry;
import com.syberos.shuili.entity.thematic.acci.AcciEntry;
import com.syberos.shuili.entity.thematic.haz.HazEntry;
import com.syberos.shuili.entity.thematic.hidden.HiddenEntry;
import com.syberos.shuili.entity.thematic.sins.SinsEntry;
import com.syberos.shuili.entity.thematic.stans.StanDirectEntry;
import com.syberos.shuili.entity.thematic.stans.StanSuperviseEntry;
import com.syberos.shuili.entity.thematic.suen.SuenEntry;
import com.syberos.shuili.entity.thematic.wins.WinsEntry;
import com.syberos.shuili.entity.thematic.woas.WoasEntry;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.syberos.shuili.config.GlobleConstants.BASE_URL;

/**
 * Created by BZB on 2018/7/13.
 * Project: Syberos.
 * Package：com.example.testmodule.testrxjavaretrofit.
 */
public class RetrofitHttpMethods {

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

    private volatile static RetrofitHttpMethods instance;

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
    public void getThematicHidden(BaseObserver<HiddenEntry> observer, String sourceType,
                                  String orgGuid,
                                  String startTime,
                                  String endTime) {

        Observable<HiddenEntry> observable = retrofitApiService.getThematicHidden(sourceType, orgGuid, startTime, endTime);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /**
     * 获取事故数据
     */
    public void getThematicAcci(BaseObserver<AcciEntry> observer, String sourceType,
                                String orgGuid,
                                String startTime,
                                String endTime) {
        Observable<AcciEntry> observable = retrofitApiService.getThematicAcci(sourceType, orgGuid, startTime, endTime);
        observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
    /**
     *  获取危险源数据
     */
    public void getThematicHaz(BaseObserver<HazEntry> observer, String sourceType,
                                 String orgGuid) {
        Observable<HazEntry> observable = retrofitApiService.getThematicHaz(sourceType, orgGuid);
        observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
    /**
     * 获取标准化数据 -- 监管数据
     */
    public void getThematicStans(BaseObserver<StanSuperviseEntry> observer, String sourceType,
                                 String orgGuid) {
        Observable<StanSuperviseEntry> observable = retrofitApiService.getThematicStan(sourceType, orgGuid);
        observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
    /**
     * 获取标准化数据 -- 直管数据
     */
    public void getThematicStansDirect(BaseObserver<StanDirectEntry> observer, String sourceType,
                                       String orgGuid) {
        Observable<StanDirectEntry> observable = retrofitApiService.getThematicStanDirect(sourceType, orgGuid);
        observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 获取水利稽察数据
     */
    public void getThematicWins(BaseObserver<WinsEntry> observer, String sourceType,
                                String orgGuid,
                                String subSourceType,
                                String startTime,
                                String endTime) {
        Observable<WinsEntry> observable = retrofitApiService.getThematicWins(sourceType, orgGuid, subSourceType, startTime, endTime);
        observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 工作考核 专题图
     */
    public void getThematicWoas(BaseObserver<WoasEntry> observer, String sourceType,
                                String orgGuid,
                                String startTime,
                                String endTime) {
        Observable<WoasEntry> observable = retrofitApiService.getThematicWoas(sourceType, orgGuid, startTime, endTime);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 安全检查 专题图
     */
    public void getThematicSins(BaseObserver<SinsEntry> observer, String sourceType,
                                String orgGuid,
                                String subSourceType,
                                String startTime,
                                String endTime) {
        Observable<SinsEntry> observable = retrofitApiService.getThematicSins(sourceType, orgGuid, subSourceType,startTime, endTime);
        observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
    /**
     * 监督执法 专题图
     */
    public void getThematicSuen(BaseObserver<SuenEntry> observer, String sourceType,
                                String orgGuid) {
        Observable<SuenEntry> observable = retrofitApiService.getThematicSuen(sourceType, orgGuid);
        observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //    安全云-工程的
    public void getSecurityOrgData(BaseObserver<SecurityCloudOrgEntry> observer, String sourceType,
                                   String orgGuid,
                                   String safaType,
                                   String startTime,
                                   String endTime) {
        Observable<SecurityCloudOrgEntry> observable = retrofitApiService.getSecurityOrgData(sourceType, orgGuid, safaType, startTime, endTime);
        observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }
    //    安全云-区域的

    public void getSecurityAreaData(BaseObserver<SecurityCloudAreaEntry> observer, String sourceType,
                                    String orgGuid,
                                    String safaType,
                                    String startTime,
                                    String endTime) {
        Observable<SecurityCloudAreaEntry> observable = retrofitApiService.getSecurityAreaData(sourceType, orgGuid, safaType, startTime, endTime);
        observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

//        tag = "getSecurityAreaData";
//        RxApiManager.get().add(tag, observable.subscribe());


    }

}
