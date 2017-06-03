package com.yzeng.hackernews.presenter;

import com.yzeng.hackernews.model.NewsInteractor;
import com.yzeng.hackernews.model.PicsInteractor;
import com.yzeng.hackernews.view.NewsView;
import com.yzeng.hackernews.view.PicsView;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class PicsListPresenterImpl implements PicsListPresenter {

    @Inject
    PicsInteractor interactor;

    private PicsView view;
    private Subscription subscription = Subscriptions.empty();

    @Inject
    public PicsListPresenterImpl() {
    }

    @Override
    public void setView(PicsView view) {
        this.view = view;
    }

    @Override
    public void destroy() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
        view = null;
    }


    @Override
    public void loadsListData() {
        if (null != view) {
            view.showProgress();
        }

        subscription = interactor.loadLatestPic()
                .subscribe(offerResponse ->
                        {
                            view.hideProgress();
                            view.setPicsValue(offerResponse.getImages());
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
