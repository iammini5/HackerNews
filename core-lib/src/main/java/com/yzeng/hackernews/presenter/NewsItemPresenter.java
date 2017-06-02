package com.yzeng.hackernews.presenter;

import com.hackernews.api.Item;
import com.yzeng.hackernews.view.ItemView;


public interface NewsItemPresenter extends BasePresenter<ItemView> {
    void loadNewsCommentsData(Item itemList);
}
