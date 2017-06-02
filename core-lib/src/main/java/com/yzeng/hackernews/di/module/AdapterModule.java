package com.yzeng.hackernews.di.module;

import com.yzeng.hackernews.presenter.NewsBasePresenter;
import com.yzeng.hackernews.presenter.NewsBasePresenterImpl;
import com.yzeng.hackernews.di.scope.NewsScope;
import com.yzeng.hackernews.view.BaseNewView;

import dagger.Module;
import dagger.Provides;

@Module
public class AdapterModule {

    private BaseNewView view;

    public AdapterModule(BaseNewView view) {
        this.view = view;
    }

    @Provides
    @NewsScope
    public BaseNewView provideAdapterView() {
        return view;
    }

    @Provides
    @NewsScope
    public NewsBasePresenter providePresenter(NewsBasePresenterImpl presenter) {
        presenter.setView(view);
        return presenter;
    }

}
