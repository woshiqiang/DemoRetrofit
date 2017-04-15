package com.hbck.demoretrofit.api;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017-04-14.
 */

public interface ApiService {
    @POST("RestImei")
    Flowable<ResponseBody> getBaidu(@Query("empid") String empid);

    @POST("RestImei")
    Observable<ResponseBody> unbind(@Query("empid") String empid);

//    @POST("Login")
//    Observable<ResponseBody> login(@Query("name") String name, @Query("pwd") String password, @Query("IMEI") String IMIE);

    @POST("Login")
    Observable<User> login(@Query("name") String name, @Query("pwd") String password, @Query("IMEI") String IMIE);

}
