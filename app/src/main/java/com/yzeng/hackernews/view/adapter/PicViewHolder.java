package com.yzeng.hackernews.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hackernews.api.Item;
import com.splashbase.api.Picture;
import com.squareup.picasso.Picasso;
import com.yzeng.hackernews.R;
import com.yzeng.hackernews.presenter.NewsBasePresenter;
import com.yzeng.hackernews.view.BaseNewView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PicViewHolder extends RecyclerView.ViewHolder {

    public final View view;
    Picture item;

    @BindView(R.id.information_layout_loading_bar)
    ProgressBar loadingBar;
    @BindView(R.id.layout_item_title)
    TextView itemTitle;
    @BindView(R.id.layout_item_image)
    ImageView itemImage;
    @BindView(R.id.information_layout_loading_error)
    TextView loadingError;

    public PicViewHolder(View view) {
        super(view);
        this.view = view;
        ButterKnife.bind(this, view);
    }

    public void showProgress() {
        loadingBar.setVisibility(View.VISIBLE);
        itemTitle.setVisibility(View.INVISIBLE);
        itemImage.setVisibility(View.INVISIBLE);
    }


    public void hideProgress() {
        loadingBar.setVisibility(View.INVISIBLE);
    }

    public void setValue(Picture newItem) {
        String src = newItem.getUrl();
        String description = String.valueOf(newItem.getId()) ;
        this.item = newItem;
        itemTitle.setVisibility(View.VISIBLE);
        itemImage.setVisibility(View.VISIBLE);
        loadingError.setVisibility(View.INVISIBLE);
        itemTitle.setText(description);
        Picasso.with(view.getContext()).load(src).into(itemImage);
    }

}
