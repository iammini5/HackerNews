package com.yzeng.hackernews.model;

import com.hackernews.api.Item;
import com.splashbase.api.Picture;
import com.splashbase.api.PictureSet;
import com.yzeng.hackernews.util.ItemPair;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


public interface NewsInteractor {
    Observable<String[]> loadTopNews();
    Observable<Item> loadNews(String itemId);
    Observable<ItemPair<Item,List<Item>>> loadNewsWithComments(Item item);

    Observable<String[]> loadNewStories();
    Observable<String[]> loadBestStories();

    void onDestroy();
}
