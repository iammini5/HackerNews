package com.yzeng.hackernews.di.module;

import com.yzeng.hackernews.presenter.NewsListPresenter;
import com.yzeng.hackernews.presenter.NewsListPresenterImpl;
import com.yzeng.hackernews.di.scope.NewsScope;
import com.yzeng.hackernews.model.NewsInteractor;
import com.yzeng.hackernews.model.NewsInteractorImpl;
import com.yzeng.hackernews.view.BaseNewView;
import com.yzeng.hackernews.view.NewsView;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsModule {

    private NewsView view;
    private BaseNewView baseView;

    public NewsModule(NewsView view) {
        this.view = view;
    }

    @Provides
    public NewsView provideNewsView() {
        return view;
    }

    @Provides
    @NewsScope
    public NewsInteractor provideInteractor(NewsInteractorImpl interactor) {
        return interactor;
    }

    @Provides
    @NewsScope
    public NewsListPresenter providePresenter(NewsListPresenterImpl presenter) {
        presenter.setView(view);
        return presenter;
    }

}
