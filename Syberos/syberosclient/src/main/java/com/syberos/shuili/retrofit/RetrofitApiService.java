package com.syberos.shuili.retrofit;

import com.syberos.shuili.entity.thematic.hidden.HiddenEntry;
import com.syberos.shuili.entity.thematic.hidden.HiddenEntryTest;
import com.syberos.shuili.entity.thematicchart.accident.AccidentDetailEntry;
import com.syberos.shuili.entity.thematicchart.hidden.HiddenDetailEntry;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by BZB on 2018/7/13.
 * Project: Syberos.
 * Package：com.example.testmodule.testrxjavaretrofit.
 */
public interface RetrofitApiService {
    //    专题图——隐患
    @POST("/desu/serv/v1/hidd")
    Observable<HiddenEntryTest> getThematicHidden(@Query("sourceType") String sourceType,
                                                  @Query("orgGuid") String orgGuid,
                                                  @Query("startTime") String startTime,
                                                  @Query("endTime") String endTime);

}
