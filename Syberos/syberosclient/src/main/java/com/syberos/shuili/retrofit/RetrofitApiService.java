package com.syberos.shuili.retrofit;

import com.syberos.shuili.entity.thematic.acci.AcciEntry;
import com.syberos.shuili.entity.thematic.hidden.HiddenEntry;
import com.syberos.shuili.entity.thematic.wins.WinsEntry;

import io.reactivex.Observable;
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
    Observable<HiddenEntry> getThematicHidden(@Query("sourceType") String sourceType,
                                              @Query("orgGuid") String orgGuid,
                                              @Query("startTime") String startTime,
                                              @Query("endTime") String endTime);

    // 专题图--- 事故
    @POST("/desu/serv/v1/acci")
    Observable<AcciEntry> getThematicAcci(@Query("sourceType") String sourceType,
                                          @Query("orgGuid") String orgGuid,
                                          @Query("startTime") String startTime,
                                          @Query("endTime") String endTime);

    @POST("/desu/serv/v1/wins")
    Observable<WinsEntry> getThematicWins(@Query("sourceType") String sourceType,
                                          @Query("orgGuid") String orgGuid,
                                          @Query("subSourceType") String subSourceType,
                                          @Query("startTime") String startTime,
                                          @Query("endTime") String endTime);
}
