package com.yzeng.hackernews.view;

import com.hackernews.api.Item;
import com.splashbase.api.Picture;

public interface BasePicView {
    void showProgress();

    void hideProgress();

    void setValue(Picture picture);

    void showRetryMessage();
}
