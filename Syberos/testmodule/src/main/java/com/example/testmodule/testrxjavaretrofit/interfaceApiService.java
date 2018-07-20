package com.example.testmodule.testrxjavaretrofit;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by BZB on 2018/7/13.
 * Project: Syberos.
 * Package：com.example.testmodule.testrxjavaretrofit.
 */
public interface interfaceApiService {
    @GET("xiaohua.json")
    Observable<List<MyJoke>> getData();
}
