package com.yzeng.hackernews.di.module;

import com.yzeng.hackernews.di.scope.NewsScope;
import com.yzeng.hackernews.di.scope.PicScope;
import com.yzeng.hackernews.model.NewsInteractor;
import com.yzeng.hackernews.model.NewsInteractorImpl;
import com.yzeng.hackernews.model.PicsInteractor;
import com.yzeng.hackernews.model.PicsInteractorImpl;
import com.yzeng.hackernews.presenter.NewsListPresenter;
import com.yzeng.hackernews.presenter.NewsListPresenterImpl;
import com.yzeng.hackernews.presenter.PicsListPresenter;
import com.yzeng.hackernews.presenter.PicsListPresenterImpl;
import com.yzeng.hackernews.view.BaseNewView;
import com.yzeng.hackernews.view.NewsView;
import com.yzeng.hackernews.view.PicsView;

import dagger.Module;
import dagger.Provides;

@Module
public class PicsModule {

    private PicsView view;

    public PicsModule(PicsView view) {
        this.view = view;
    }

    @Provides
    public PicsView provideNewsView() {
        return view;
    }

    @Provides
    @PicScope
    public PicsInteractor provideInteractor(PicsInteractorImpl interactor) {
        return interactor;
    }

    @Provides
    @PicScope
    public PicsListPresenter providePresenter(PicsListPresenterImpl presenter) {
        presenter.setView(view);
        return presenter;
    }

}
