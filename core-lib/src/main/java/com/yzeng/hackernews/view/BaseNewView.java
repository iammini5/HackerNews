package com.yzeng.hackernews.view;

import com.hackernews.api.Item;

public interface BaseNewView {
    void showProgress();

    void hideProgress();

    void setValue(Item offers);

    void showRetryMessage();
}
