package com.yzeng.hackernews.presenter;

import com.hackernews.api.Item;
import com.yzeng.hackernews.model.NewsInteractor;
import com.yzeng.hackernews.view.ItemView;
import javax.inject.Inject;
import rx.Subscription;
import rx.subscriptions.Subscriptions;


public class NewsItemPresenterImpl implements NewsItemPresenter {

    @Inject
    NewsInteractor interactor;


    private ItemView view;
    private Subscription subscription = Subscriptions.empty();
    private Subscription commentsubscription = Subscriptions.empty();

    @Inject
    public NewsItemPresenterImpl() {
    }

    @Override
    public void setView(ItemView view) {
        this.view = view;
    }

    @Override
    public void destroy() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
        view = null;
    }

    @Override
    public void loadNewsCommentsData(Item item) {
        if (null != view) {
            view.showProgress();
        }

        commentsubscription = interactor.loadNewsWithComments(item)
                .subscribe(
                        itemPair -> view.showComments(itemPair.getKey(), itemPair.getValue()),
                        //throw error on network issues
                        throwable -> {
                            if (null != view) {
                                view.hideProgress();
                                view.showMessage(throwable.getMessage());
                                view.showOfflineMessage();
                            }
                        },
                        () -> {
                            if (null != view) {
                                view.hideProgress();
                            }
                        }
                );

    }
}
