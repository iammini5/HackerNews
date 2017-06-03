package com.yzeng.hackernews.client;

import com.hackernews.api.Item;
import com.splashbase.api.Picture;
import com.splashbase.api.PictureSet;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface SplashBaseApi {

    @GET("latest")
    Observable<PictureSet> listLatestPic();

    @GET("random")
    Observable<Picture> listRandomPic();

    @GET("{id}")
    Observable<Picture> getPictureById(@Path("id") String itemId);


    @GET("sources/{id}")
    Observable<PictureSet> getPicturesBySrc(@Path("id") String source);

}
