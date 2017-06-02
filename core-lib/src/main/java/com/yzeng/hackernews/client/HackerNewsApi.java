package com.yzeng.hackernews.client;


import com.hackernews.api.Item;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface HackerNewsApi {

    @GET("topstories.json")
    Observable<String[]> listItemIndex();

    @GET("item/{id}.json")
    Observable<Item> getItem(@Path("id") String itemId);
}
