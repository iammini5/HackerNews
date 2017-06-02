package com.yzeng.hackernews.presenter;

import com.yzeng.hackernews.view.NewsView;


public interface NewsListPresenter extends BasePresenter<NewsView> {

    void loadNewsListData();

}
