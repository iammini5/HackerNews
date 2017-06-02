package com.yzeng.hackernews.model;

import com.hackernews.api.Item;
import com.yzeng.hackernews.util.ItemPair;

import java.util.List;

import rx.Observable;



public interface NewsInteractor {
    Observable<String[]> loadTopNews();
    Observable<Item> loadNews(String itemId);
    Observable<ItemPair<Item,List<Item>>> loadNewsWithComments(Item item);
    void onDestroy();
}
