package com.yzeng.hackernews.presenter;


import com.yzeng.hackernews.view.BaseNewView;

public interface NewsBasePresenter extends BasePresenter<BaseNewView> {
    void fetchData(String index);
}
