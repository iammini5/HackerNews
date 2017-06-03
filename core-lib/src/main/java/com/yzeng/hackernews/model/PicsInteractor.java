package com.yzeng.hackernews.model;

import com.hackernews.api.Item;
import com.splashbase.api.Picture;
import com.splashbase.api.PictureSet;
import com.yzeng.hackernews.util.ItemPair;

import java.util.List;

import rx.Observable;


public interface PicsInteractor {
    Observable<Picture> loadPictureById(String itemId);
    Observable<PictureSet> loadPicturesBySrc(Item item);
    Observable<PictureSet> loadLatestPic();
    Observable<Picture> loadRandomPic();

    void onDestroy();
}
