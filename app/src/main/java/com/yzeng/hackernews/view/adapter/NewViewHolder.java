package com.yzeng.hackernews.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.hackernews.api.Item;
import com.yzeng.hackernews.R;
import com.yzeng.hackernews.di.module.AdapterModule;
import com.yzeng.hackernews.presenter.NewsBasePresenter;
import com.yzeng.hackernews.view.BaseNewView;
import com.yzeng.hackernews.view.fragment.NewsFragment;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;


public class NewViewHolder extends RecyclerView.ViewHolder implements BaseNewView {

    public final View view;
    Item item;

    @Inject
    public NewsBasePresenter presenter;

    @BindView(R.id.information_layout_loading_bar)
    ProgressBar loadingBar;
    @BindView(R.id.layout_item_title)
    TextView itemTitle;
    @BindView(R.id.layout_item_description)
    TextView itemDescription;
    @BindView(R.id.information_layout_loading_error)
    TextView loadingError;

    public NewViewHolder(View view, NewsFragment fragment) {
        super(view);
        this.view = view;
        ButterKnife.bind(this, view);

        fragment.getDependenciesModules()
                .plus(new AdapterModule(this))
                .inject(this);
    }

    @Override
    public void showProgress() {
        loadingBar.setVisibility(View.VISIBLE);
        itemTitle.setVisibility(View.INVISIBLE);
        itemDescription.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        loadingBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setValue(Item newItem) {
        String title = newItem.getTitle();
        String description = Item.getDescription(newItem);
        this.item = newItem;
        itemTitle.setVisibility(View.VISIBLE);
        itemDescription.setVisibility(View.VISIBLE);
        loadingError.setVisibility(View.INVISIBLE);
        itemTitle.setText(title);
        itemDescription.setText(description);
    }


    @Override
    public void showRetryMessage() {
        loadingError.setVisibility(View.VISIBLE);
        loadingError.setText(R.string.offline);
    }

}
