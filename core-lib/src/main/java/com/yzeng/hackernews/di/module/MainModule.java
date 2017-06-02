package com.yzeng.hackernews.di.module;

import com.yzeng.hackernews.presenter.MainPresenter;
import com.yzeng.hackernews.presenter.MainPresenterImpl;
import com.yzeng.hackernews.di.scope.MainScope;
import com.yzeng.hackernews.view.MainView;

import dagger.Module;
import dagger.Provides;


@Module
public class MainModule {

    private MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Provides
    public MainView provideView() {
        return view;
    }

    @Provides
    @MainScope
    public MainPresenter providePresenter(MainPresenterImpl presenter) {
        presenter.setView(view);
        return presenter;
    }

}
