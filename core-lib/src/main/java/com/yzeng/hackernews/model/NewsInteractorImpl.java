package com.yzeng.hackernews.model;

import com.hackernews.api.Item;
import com.yzeng.hackernews.client.HackerNewsApi;
import com.yzeng.hackernews.util.ItemPair;
import com.yzeng.hackernews.util.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscription;
import rx.subjects.ReplaySubject;


@Singleton
public class NewsInteractorImpl implements NewsInteractor {
    private HackerNewsApi api;
    private SchedulerProvider scheduler;

    private ReplaySubject<String[]> topNewsSubject;
    private ReplaySubject<Item> itemSubject;
    private ReplaySubject<ItemPair<Item,List<Item>>> commentsAndReplySubject;


    private Subscription topNewsSubscription;
    private Subscription itemSubscription;
    private Subscription commentsSubscription;

    int maxRefreshTimeout;
    int maxRefreshCount;

    @Inject
    public NewsInteractorImpl(HackerNewsApi api, SchedulerProvider scheduler,
                              @Named("maxTimeForRefresh")int maxRefreshTimeout,
                              @Named("maxCountForRefresh")int maxRefreshCount) {
        this.api = api;
        this.scheduler = scheduler;
        this.maxRefreshTimeout = maxRefreshTimeout;
        this.maxRefreshCount = maxRefreshCount;
    }

    @Override
    public Observable<String[]> loadTopNews() {
        if (topNewsSubscription == null || topNewsSubscription.isUnsubscribed()) {
            topNewsSubject = ReplaySubject.create();


            topNewsSubscription = api.listItemIndex()
                    .subscribeOn(scheduler.backgroundThread())
                    .observeOn(scheduler.mainThread())
                    .subscribe(topNewsSubject);
        }

        return topNewsSubject.asObservable();
    }

    @Override
    public Observable<Item> loadNews(String itemId) {
        {
            itemSubject = ReplaySubject.create();


            itemSubscription = api.getItem(itemId)
                    .subscribeOn(scheduler.backgroundThread())
                    .observeOn(scheduler.mainThread())
                    .subscribe(itemSubject);
        }
        return itemSubject.asObservable();
    }

    @Override
    public Observable<ItemPair<Item, List<Item>>> loadNewsWithComments(Item item) {
        if(null != commentsSubscription && commentsSubscription.isUnsubscribed())
        {
            commentsSubscription.unsubscribe();
            commentsAndReplySubject = null;
        }
        {
            commentsAndReplySubject = ReplaySubject.create();
            Integer[] integers = (
                    null == item.getKids() ?
                            new Integer[0]:
                            item.getKids().toArray(new Integer[item.getKids().size()])
            );
            commentsSubscription =
                    Observable.from( integers )
                            .flatMap( integer -> api.getItem( String.valueOf(integer))
                                    .flatMap( response-> {
                                        if(response.getKids() == null) {
                                            return Observable.just(new ItemPair<Item, List<Item>>(response, new ArrayList<>()));
                                        }
                                        else
                                        {
                                            return
                                                 Observable.from(response.getKids())
                                                 .flatMap( kidsIndex -> api.getItem( String.valueOf(kidsIndex)))
                                                         .toList()
                                                         .flatMap( kidsList-> Observable.just(
                                                                 new ItemPair<Item, List<Item>>(response, kidsList))  );

                                        }
                                    })
                            )
//                            .buffer(maxRefreshTimeout, TimeUnit.SECONDS,maxRefreshCount)
//                            .filter(items -> items.size() > 0)
                            .subscribeOn(scheduler.backgroundThread())
                            .observeOn(scheduler.mainThread())
                            .subscribe(commentsAndReplySubject);


        }
        return commentsAndReplySubject.asObservable();
    }

    @Override
    public void onDestroy() {
        if (topNewsSubscription != null && !topNewsSubscription.isUnsubscribed())
            topNewsSubscription.unsubscribe();


        if (itemSubscription != null && !itemSubscription.isUnsubscribed())
            itemSubscription.unsubscribe();

        if(commentsSubscription != null && !commentsSubscription.isUnsubscribed())
            commentsSubscription.unsubscribe();
    }
}
