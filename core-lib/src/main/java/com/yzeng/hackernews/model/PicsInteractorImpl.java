package com.yzeng.hackernews.model;

import com.hackernews.api.Item;
import com.splashbase.api.Picture;
import com.splashbase.api.PictureSet;
import com.yzeng.hackernews.client.HackerNewsApi;
import com.yzeng.hackernews.client.SplashBaseApi;
import com.yzeng.hackernews.util.ItemPair;
import com.yzeng.hackernews.util.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscription;
import rx.subjects.ReplaySubject;

@Singleton
public class PicsInteractorImpl implements PicsInteractor {
    private SplashBaseApi api;

    private ReplaySubject<Picture> itemSubject;
    private SchedulerProvider scheduler;

    private ReplaySubject<PictureSet> itemSetSubject;

    private Subscription itemSetSubscription;
    private Subscription itemSubscription;

    int maxRefreshTimeout;
    int maxRefreshCount;

    @Inject
    public PicsInteractorImpl(SplashBaseApi api, SchedulerProvider scheduler,
                              @Named("maxTimeForRefresh")int maxRefreshTimeout,
                              @Named("maxCountForRefresh")int maxRefreshCount) {
        this.api = api;
        this.scheduler = scheduler;
        this.maxRefreshTimeout = maxRefreshTimeout;
        this.maxRefreshCount = maxRefreshCount;
    }

    @Override
    public Observable<Picture> loadPictureById(String itemId) {
        {
            itemSubject = ReplaySubject.create();

            api.getPictureById(itemId)
                    .subscribeOn(scheduler.backgroundThread())
                    .observeOn(scheduler.mainThread())
                    .subscribe(itemSubject);
        }
        return itemSubject.asObservable();
    }

    @Override
    public Observable<PictureSet> loadPicturesBySrc(Item item) {
        return null;
    }

    @Override
    public Observable<PictureSet> loadLatestPic() {
        {
            itemSetSubject = ReplaySubject.create();

            api.listLatestPic()
                    .subscribeOn(scheduler.backgroundThread())
                    .observeOn(scheduler.mainThread())
                    .subscribe(itemSetSubject);
        }
        return itemSetSubject.asObservable();
    }

    @Override
    public Observable<Picture> loadRandomPic() {
        return null;
    }

    @Override
    public void onDestroy() {

    }
}
