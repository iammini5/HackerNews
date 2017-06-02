package com.yzeng.hackernews.presenter;


import com.yzeng.hackernews.di.scope.NewsScope;
import com.yzeng.hackernews.model.NewsInteractor;
import com.yzeng.hackernews.view.BaseNewView;
import javax.inject.Inject;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

@NewsScope
public class NewsBasePresenterImpl implements NewsBasePresenter {

    @Inject
    public NewsInteractor interactor;

    private BaseNewView view;
    private Subscription subscription = Subscriptions.empty();

    @Inject
    public NewsBasePresenterImpl() {
    }

    @Override
    public void setView(BaseNewView view) {
        this.view = view;
    }

    @Override
    public void destroy() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
        view = null;
    }

    @Override
    public void fetchData(String index) {
        if (null != view) {
            view.showProgress();
        }

        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();

        subscription = interactor.loadNews(index)
                .subscribe(newResponse ->
                        {
                            view.hideProgress();
                            view.setValue(newResponse);
                        },

                        //throw error on network issues
                        throwable -> {
                            if (null != view) {
                                view.hideProgress();
                                view.showRetryMessage();
                            }
                        });
    }
}
