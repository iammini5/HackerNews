package com.yzeng.hackernews.view;


public interface NewsView extends BaseView {

    void showProgress();

    void hideProgress();

    void setNewsValue(String[] offers);

}
