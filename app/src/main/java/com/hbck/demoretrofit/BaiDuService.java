package com.hbck.demoretrofit;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface BaiDuService {
    @GET("/")
    Flowable<ResponseBody> getText();
}