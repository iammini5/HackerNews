package com.yzeng.hackernews.view;

import com.hackernews.api.Item;

import java.io.Serializable;

public interface BaseNewView {
    void showProgress();

    void hideProgress();

    void setValue(Item item);

    void showRetryMessage();
}
