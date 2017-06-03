package com.yzeng.hackernews.view;


import com.splashbase.api.Picture;
import com.splashbase.api.PictureSet;

public interface PicsView extends BaseView {

    void showProgress();

    void hideProgress();

    void setPicsValue(Picture[] pictureSet);

}
