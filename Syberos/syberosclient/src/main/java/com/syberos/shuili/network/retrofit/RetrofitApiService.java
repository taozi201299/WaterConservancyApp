package com.syberos.shuili.network.retrofit;

import com.syberos.shuili.entity.thematic.acci.AcciEntry;
import com.syberos.shuili.entity.thematic.hidden.HiddenEntry;
import com.syberos.shuili.entity.thematic.sins.SinsEntry;
import com.syberos.shuili.entity.thematic.wins.WinsEntry;
import com.syberos.shuili.entity.thematic.woas.WoasEntry;

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

    //水利稽查
    @POST("/desu/serv/v1/wins")
    Observable<WinsEntry> getThematicWins(@Query("sourceType") String sourceType,
                                          @Query("orgGuid") String orgGuid,
                                          @Query("subSourceType") String subSourceType,
                                          @Query("startTime") String startTime,
                                          @Query("endTime") String endTime);

    //    专题图—— 工作考核
    @POST("/desu/serv/v1/woas")
    Observable<WoasEntry> getThematicWoas(@Query("sourceType") String sourceType,
                                          @Query("orgGuid") String orgGuid,
                                          @Query("startTime") String startTime,
                                          @Query("endTime") String endTime);

    //    专题图—— 危险源
    @POST("/desu/serv/v1/getWxyCount")
    Observable<WoasEntry> getThematicHaz(@Query("sourceType") String sourceType,
                                         @Query("orgGuid") String orgGuid,
                                         @Query("startTime") String startTime,
                                         @Query("endTime") String endTime);

    //    专题图—— 标准化
    @POST("/desu/serv/v1/getCriterionCount")
    Observable<WoasEntry> getThematicStan(@Query("sourceType") String sourceType,
                                          @Query("orgGuid") String orgGuid,
                                          @Query("startTime") String startTime,
                                          @Query("endTime") String endTime);

    //    专题图—— 监督执法
    @POST("/desu/serv/v1/getCaseCount")
    Observable<WoasEntry> getThematicSuen(@Query("sourceType") String sourceType,
                                          @Query("orgGuid") String orgGuid,
                                          @Query("startTime") String startTime,
                                          @Query("endTime") String endTime);

    //    专题图—— 安全检查
    @POST("/desu/serv/v1/sins")
    Observable<SinsEntry> getThematicSins(@Query("sourceType") String sourceType,
                                          @Query("orgGuid") String orgGuid,
                                          @Query("startTime") String startTime,
                                          @Query("endTime") String endTime);

}