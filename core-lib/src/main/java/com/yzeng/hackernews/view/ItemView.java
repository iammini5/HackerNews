package com.yzeng.hackernews.view;

import com.hackernews.api.Item;

import java.util.List;

public interface ItemView extends BaseView{

    void showProgress();

    void hideProgress();

    void showComments(Item parent, List<Item> comments);
}
