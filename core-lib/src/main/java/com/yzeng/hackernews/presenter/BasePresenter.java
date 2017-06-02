package com.yzeng.hackernews.presenter;


public interface BasePresenter<T> {

    void setView(T view);

    void destroy();

}
