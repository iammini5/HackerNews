package com.yzeng.hackernews.presenter;

import com.yzeng.hackernews.view.MainView;

import javax.inject.Inject;

public class MainPresenterImpl implements MainPresenter {

    private MainView view;

    @Inject
    public MainPresenterImpl() {
    }

    @Override
    public void setView(MainView view) {
        this.view = view;
    }

    @Override
    public void destroy() {
        view = null;
    }

}
