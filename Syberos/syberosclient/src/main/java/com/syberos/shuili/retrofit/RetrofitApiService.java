package com.syberos.shuili.retrofit;

import com.syberos.shuili.entity.thematicchart.accident.AccidentDetailEntry;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by BZB on 2018/7/13.
 * Project: Syberos.
 * Package：com.example.testmodule.testrxjavaretrofit.
 */
public interface RetrofitApiService {
//    专题图——隐患
    Observable<AccidentDetailEntry> getAccidentDetail();
}
