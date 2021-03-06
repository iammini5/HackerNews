package com.yzeng.hackernews.presenter;

import com.yzeng.hackernews.model.NewsInteractor;
import com.yzeng.hackernews.view.NewsView;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.Subscriptions;


public class NewsListPresenterImpl implements NewsListPresenter {

    @Inject
    NewsInteractor interactor;

    private NewsView view;
    private Subscription subscription = Subscriptions.empty();

    @Inject
    public NewsListPresenterImpl() {
    }

    @Override
    public void setView(NewsView view) {
        this.view = view;
    }

    @Override
    public void destroy() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
        view = null;
    }

    @Override
    public void loadNewsListData(int type) {
        if (null != view) {
            view.showProgress();
        }
        Observable<String[]> observable;
        switch (type)
        {
            case TOP_STORY_LIST:
                observable = interactor.loadTopNews();
                break;
            case LATEST_STORY_LIST:
                observable = interactor.loadNewStories();
                break;
            case BEST_STORY_LIST:
                observable = interactor.loadBestStories();
                break;
            default:
                view.showOfflineMessage();
                return;
        }

        subscription = observable
                .subscribe(offerResponse ->
                        {
                            String[] arrayItem = (offerResponse);
                            view.hideProgress();
                            view.setNewsValue(arrayItem);
                        },

                        //throw error on network issues
                        throwable -> {
                            if (null != view) {
                                view.hideProgress();
                                view.showMessage(throwable.getMessage());
                                view.showOfflineMessage();
                            }
                        });
    }

}
