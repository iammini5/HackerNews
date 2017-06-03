package com.yzeng.hackernews.presenter;

import com.yzeng.hackernews.view.NewsView;


public interface NewsListPresenter extends BasePresenter<NewsView> {

    public static final int TOP_STORY_LIST = 0;
    public static final int LATEST_STORY_LIST = 1;
    public static final int BEST_STORY_LIST = 2;

    void loadNewsListData(int type);

}
