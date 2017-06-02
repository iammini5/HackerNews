package com.yzeng.hackernews.di.module;

import com.yzeng.hackernews.presenter.NewsItemPresenter;
import com.yzeng.hackernews.presenter.NewsItemPresenterImpl;
import com.yzeng.hackernews.di.scope.ItemScope;
import com.yzeng.hackernews.model.NewsInteractor;
import com.yzeng.hackernews.model.NewsInteractorImpl;
import com.yzeng.hackernews.view.ItemView;

import dagger.Module;
import dagger.Provides;

@Module
public class ItemModule {

    private ItemView view;

    public ItemModule(ItemView view) {
        this.view = view;
    }

    @Provides
    public ItemView provideView() {
        return view;
    }

    @Provides
    @ItemScope
    public NewsInteractor provideInteractor(NewsInteractorImpl interactor) {
        return interactor;
    }

    @Provides
    @ItemScope
    public NewsItemPresenter providePresenter(NewsItemPresenterImpl presenter) {
        presenter.setView(view);
        return presenter;
    }

}
